package com.nhom10.aifitnutrition.ai

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

data class FoodAnalysisResult(
    val foodName: String,
    val calories: Int,
    val proteinG: Float,
    val carbsG: Float,
    val fatG: Float,
    val portionGrams: Float,
    val healthNote: String = ""
)

class GeminiService(apiKey: String) {

    private val normalizedApiKey = apiKey.trim()
    private val modelCandidates = listOf(
        "gemini-1.5-flash-latest",
        "gemini-flash-latest",
        "gemini-1.5-flash"
    )

    private fun buildVisionModel(modelName: String) = GenerativeModel(
        modelName = modelName,
        apiKey = normalizedApiKey,
        generationConfig = generationConfig {
            temperature = 0.2f
            maxOutputTokens = 700
        }
    )

    private fun buildChatModel(modelName: String) = GenerativeModel(
        modelName = modelName,
        apiKey = normalizedApiKey,
        generationConfig = generationConfig {
            temperature = 0.7f
            maxOutputTokens = 700
        }
    )

    suspend fun analyzeFoodImage(bitmap: Bitmap): Result<FoodAnalysisResult> =
        withContext(Dispatchers.IO) {
            try {
                if (normalizedApiKey.isBlank()) {
                    return@withContext Result.failure(
                        IllegalStateException("GEMINI_API_KEY is missing. Please add it to local.properties.")
                    )
                }

                val prompt = """
                    Analyze this food image and respond with ONLY a JSON object (no markdown, no explanation):
                    {
                      "foodName": "name of the food",
                      "calories": estimated_calories_as_integer,
                      "proteinG": protein_in_grams_as_float,
                      "carbsG": carbohydrates_in_grams_as_float,
                      "fatG": fat_in_grams_as_float,
                      "portionGrams": estimated_portion_weight_as_float,
                      "healthNote": "brief one-sentence health note"
                    }
                    Estimate for the visible portion size. Be accurate and concise.
                """.trimIndent()

                val inputContent = content {
                    image(bitmap)
                    text(prompt)
                }

                val response = generateVisionWithFallback(inputContent)
                val rawText = response.text ?: return@withContext Result.failure(
                    Exception("Empty response from Gemini")
                )

                // Clean JSON from possible markdown code blocks and extract first JSON object.
                val cleanedText = rawText
                    .replace("```json", "")
                    .replace("```", "")
                    .trim()
                val jsonStart = cleanedText.indexOf('{')
                val jsonEnd = cleanedText.lastIndexOf('}')
                if (jsonStart == -1 || jsonEnd == -1 || jsonStart >= jsonEnd) {
                    return@withContext Result.failure(
                        IllegalStateException("Gemini did not return valid JSON. Raw response: $cleanedText")
                    )
                }
                val jsonText = cleanedText.substring(jsonStart, jsonEnd + 1)

                val json = JSONObject(jsonText)
                val result = FoodAnalysisResult(
                    foodName = json.getString("foodName"),
                    calories = json.getInt("calories"),
                    proteinG = json.getDouble("proteinG").toFloat(),
                    carbsG = json.getDouble("carbsG").toFloat(),
                    fatG = json.getDouble("fatG").toFloat(),
                    portionGrams = json.getDouble("portionGrams").toFloat(),
                    healthNote = json.optString("healthNote", "")
                )
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(IllegalStateException(mapGeminiError(e), e))
            }
        }

    suspend fun sendChatMessage(
        userMessage: String,
        conversationHistory: List<Pair<String, String>>,  // role, content
        userContext: String = ""
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            if (normalizedApiKey.isBlank()) {
                return@withContext Result.failure(
                    IllegalStateException("GEMINI_API_KEY is missing. Please add it to local.properties.")
                )
            }

            val systemContext = """
                You are an expert AI health and fitness coach named FitBot.
                You provide personalized, science-based nutrition and fitness advice.
                Be concise, encouraging, and practical. Use emojis sparingly.
                $userContext
            """.trimIndent()

            val fullPrompt = buildString {
                append(systemContext)
                append("\n\n")
                // Add recent history
                conversationHistory.takeLast(4).forEach { (role, content) ->
                    val roleLabel = if (role == "user") "User" else "Assistant"
                    append("$roleLabel: $content\n")
                }
                append("User: $userMessage\nAssistant:")
            }

            val response = generateChatWithFallback(fullPrompt)
            val text = response.text ?: "I can continue, but the answer was truncated. Please ask me to continue."
            Result.success(text.trim())
        } catch (e: Exception) {
            Result.failure(IllegalStateException(mapGeminiError(e), e))
        }
    }

    private suspend fun generateVisionWithFallback(inputContent: com.google.ai.client.generativeai.type.Content) =
        runWithModelFallback { modelName ->
            buildVisionModel(modelName).generateContent(inputContent)
        }

    private suspend fun generateChatWithFallback(prompt: String) =
        runWithModelFallback { modelName ->
            buildChatModel(modelName).generateContent(prompt)
        }

    private suspend fun <T> runWithModelFallback(block: suspend (String) -> T): T {
        var lastError: Throwable? = null
        for (model in modelCandidates) {
            try {
                return block(model)
            } catch (e: Exception) {
                lastError = e
                val msg = e.message?.lowercase().orEmpty()
                val isModelIssue = "not found" in msg || "404" in msg || "model" in msg
                if (!isModelIssue) throw e
            }
        }
        throw IllegalStateException("No available Gemini model found.", lastError)
    }

    private fun mapGeminiError(error: Throwable): String {
        val message = error.message?.lowercase().orEmpty()
        return when {
            "api key" in message || "permission" in message || "401" in message || "403" in message ->
                "Gemini authentication failed. Check API key validity and API restrictions."
            "429" in message || "quota" in message || "rate limit" in message || "too many requests" in message ->
                "Gemini quota exceeded or rate-limited (429). Wait a bit or increase quota in Google AI Studio."
            "max_tokens" in message || "finish reason: max_tokens" in message ->
                "Response exceeded token limit. Please ask a shorter question or continue in parts."
            "not found" in message || "404" in message || "model" in message ->
                "Gemini model is unavailable. Verify model name and SDK version."
            "network" in message || "timeout" in message || "unable to resolve host" in message ->
                "Network error while calling Gemini. Check internet connection."
            else ->
                error.message ?: "Unknown Gemini error."
        }
    }
}
