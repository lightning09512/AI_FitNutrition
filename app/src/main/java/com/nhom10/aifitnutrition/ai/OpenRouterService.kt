package com.nhom10.aifitnutrition.ai

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class OpenRouterService(apiKey: String) {

    private val normalizedApiKey = apiKey.trim()

    suspend fun sendChatMessage(
        userMessage: String,
        conversationHistory: List<Pair<String, String>>,
        userContext: String = ""
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            if (normalizedApiKey.isBlank()) {
                return@withContext Result.failure(
                    IllegalStateException("OPENROUTER_API_KEY is missing. Please add it to local.properties.")
                )
            }

            val url = URL("https://openrouter.ai/api/v1/chat/completions")
            val conn = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                connectTimeout = 20_000
                readTimeout = 30_000
                doOutput = true
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Authorization", "Bearer $normalizedApiKey")
                setRequestProperty("HTTP-Referer", "https://aifitnutrition.local")
                setRequestProperty("X-Title", "AI FitNutrition")
            }

            val messages = JSONArray()
            val systemContent = buildString {
                append("Bạn là FitBot, trợ lý AI chuyên về sức khỏe và thể hình.")
                append(" Luôn trả lời hoàn toàn bằng tiếng Việt, ngắn gọn, thực tế, an toàn và dễ hiểu.")
                if (userContext.isNotBlank()) append(" $userContext")
            }
            messages.put(JSONObject().put("role", "system").put("content", systemContent))

            conversationHistory.takeLast(4).forEach { (role, content) ->
                val mappedRole = if (role == "model") "assistant" else "user"
                messages.put(JSONObject().put("role", mappedRole).put("content", content.take(600)))
            }
            messages.put(JSONObject().put("role", "user").put("content", userMessage.take(800)))

            val payload = JSONObject()
                .put("model", "openai/gpt-4o-mini")
                .put("messages", messages)
                .put("temperature", 0.7)
                .put("max_tokens", 500)

            conn.outputStream.use { os ->
                os.write(payload.toString().toByteArray(Charsets.UTF_8))
            }

            val code = conn.responseCode
            val stream = if (code in 200..299) conn.inputStream else conn.errorStream
            val responseText = BufferedReader(InputStreamReader(stream)).use { reader ->
                reader.readText()
            }

            if (code !in 200..299) {
                return@withContext Result.failure(
                    IllegalStateException("OpenRouter error ($code): $responseText")
                )
            }

            val root = JSONObject(responseText)
            val choices = root.optJSONArray("choices")
            val content = choices
                ?.optJSONObject(0)
                ?.optJSONObject("message")
                ?.optString("content", "")
                ?.trim()
                .orEmpty()

            if (content.isBlank()) {
                return@withContext Result.failure(
                    IllegalStateException("OpenRouter returned empty response.")
                )
            }
            Result.success(content)
        } catch (e: Exception) {
            android.util.Log.e("OpenRouterService", "OpenRouter error", e)
            Result.failure(IllegalStateException(e.message ?: "Unknown OpenRouter error.", e))
        }
    }
}
