package com.nhom10.aifitnutrition.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nhom10.aifitnutrition.data.model.ChatMessage
import com.nhom10.aifitnutrition.databinding.ItemChatMessageBinding
import java.text.SimpleDateFormat
import java.util.*
import android.view.View

class ChatAdapter : ListAdapter<ChatMessage, ChatAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(val binding: ItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatMessageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val timeStr = timeFormat.format(Date(item.timestamp))
        with(holder.binding) {
            if (item.role == "user") {
                layoutUser.visibility = View.VISIBLE
                layoutAi.visibility = View.GONE
                tvUserMessage.text = item.content
                tvUserTime.text = timeStr
            } else {
                layoutUser.visibility = View.GONE
                layoutAi.visibility = View.VISIBLE
                tvAiMessage.text = item.content
                tvAiTime.text = timeStr
            }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<ChatMessage>() {
            override fun areItemsTheSame(a: ChatMessage, b: ChatMessage) = a.id == b.id
            override fun areContentsTheSame(a: ChatMessage, b: ChatMessage) = a == b
        }
    }
}
