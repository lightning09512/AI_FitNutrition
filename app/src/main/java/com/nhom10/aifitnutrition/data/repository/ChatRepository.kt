package com.nhom10.aifitnutrition.data.repository

import com.nhom10.aifitnutrition.data.dao.ChatMessageDao
import com.nhom10.aifitnutrition.data.model.ChatMessage
import kotlinx.coroutines.flow.Flow

class ChatRepository(private val chatMessageDao: ChatMessageDao) {

    fun getAllMessages(): Flow<List<ChatMessage>> = chatMessageDao.getAllMessages()

    suspend fun insertMessage(message: ChatMessage) = chatMessageDao.insert(message)

    suspend fun clearHistory() = chatMessageDao.clearAll()

    suspend fun getRecentMessages(limit: Int = 10): List<ChatMessage> =
        chatMessageDao.getRecentMessages(limit)
}
