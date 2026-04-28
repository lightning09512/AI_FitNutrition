package com.nhom10.aifitnutrition.ui.chat

import android.app.Application
import androidx.lifecycle.*
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.BuildConfig
import com.nhom10.aifitnutrition.ai.GeminiService
import com.nhom10.aifitnutrition.ai.OpenRouterService
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.data.model.ChatMessage
import com.nhom10.aifitnutrition.data.repository.ChatRepository
import com.nhom10.aifitnutrition.data.repository.NutritionRepository
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val chatRepo = ChatRepository(db.chatMessageDao())
    private val nutritionRepo = NutritionRepository(db.foodLogDao(), db.waterLogDao(), db.userProfileDao())
    private val geminiService = GeminiService(BuildConfig.GEMINI_API_KEY)
    private val openRouterService = OpenRouterService(BuildConfig.OPENROUTER_API_KEY)

    val messages = chatRepo.getAllMessages().asLiveData()

    private val _isTyping = MutableLiveData(false)
    val isTyping: LiveData<Boolean> = _isTyping

    init {
        insertGreetingIfEmpty()
    }

    private fun insertGreetingIfEmpty() {
        viewModelScope.launch {
            val recent = chatRepo.getRecentMessages(1)
            if (recent.isEmpty()) {
                chatRepo.insertMessage(
                    ChatMessage(
                        role = "model",
                        content = getApplication<Application>().getString(R.string.ai_greeting)
                    )
                )
            }
        }
    }

    fun sendMessage(userText: String) {
        viewModelScope.launch {
            try {
                // Save user message
                val userMsg = ChatMessage(role = "user", content = userText)
                chatRepo.insertMessage(userMsg)

                _isTyping.value = true

                // Get recent history for context
                val history = chatRepo.getRecentMessages(10)
                    .map { it.role to it.content }

                // Get user profile for context
                val profile = nutritionRepo.getUserProfileOnce()
                val userContext = profile?.let {
                    "Thông tin người dùng: Tên=${it.name}, Mục tiêu=${it.goalType}, Mục tiêu calo mỗi ngày=${it.dailyCalorieGoal}kcal, Cân nặng=${it.weightKg}kg. Luôn trả lời bằng tiếng Việt tự nhiên, dễ hiểu."
                } ?: ""

                android.util.Log.d("ChatViewModel", "Sending message: $userText")
                android.util.Log.d("ChatViewModel", "History size: ${history.size}")
                android.util.Log.d("ChatViewModel", "User context: $userContext")

                val geminiResult = geminiService.sendChatMessage(userText, history, userContext)
                val result = if (geminiResult.isSuccess) {
                    geminiResult
                } else {
                    android.util.Log.w("ChatViewModel", "Gemini failed, trying OpenRouter")
                    val fallback = openRouterService.sendChatMessage(userText, history, userContext)
                    if (fallback.isSuccess) {
                        fallback
                    } else {
                        val geminiError = geminiResult.exceptionOrNull()?.message ?: "Unknown Gemini error"
                        val openRouterError = fallback.exceptionOrNull()?.message ?: "OpenRouter fallback not configured"
                        Result.failure(IllegalStateException("$geminiError | Fallback failed: $openRouterError"))
                    }
                }
                _isTyping.value = false

                val responseText = if (result.isSuccess) {
                    result.getOrThrow()
                } else {
                    val detail = result.exceptionOrNull()?.message ?: "Unknown error"
                    android.util.Log.e("ChatViewModel", "AI response failed: $detail")
                    "Xin lỗi, AI đang gặp lỗi: $detail"
                }
                chatRepo.insertMessage(ChatMessage(role = "model", content = responseText))
            } catch (e: Exception) {
                _isTyping.value = false
                android.util.Log.e("ChatViewModel", "Unexpected error in sendMessage", e)
                chatRepo.insertMessage(ChatMessage(role = "model", content = "Xin lỗi, có lỗi không mong muốn: ${e.message}"))
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            chatRepo.clearHistory()
            insertGreetingIfEmpty()
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(application) as T
        }
    }
}
