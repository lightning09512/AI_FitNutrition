package com.nhom10.aifitnutrition.ui.chat

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhom10.aifitnutrition.databinding.FragmentChatBinding
import com.nhom10.aifitnutrition.ui.adapter.ChatAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChatViewModel by viewModels {
        ChatViewModel.Factory(requireActivity().application)
    }

    private val adapter = ChatAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvChat.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
        }
        binding.rvChat.adapter = adapter

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            adapter.submitList(messages) {
                if (messages.isNotEmpty()) {
                    binding.rvChat.smoothScrollToPosition(messages.size - 1)
                }
            }
        }

        viewModel.isTyping.observe(viewLifecycleOwner) { typing ->
            binding.layoutTyping.visibility = if (typing) View.VISIBLE else View.GONE
        }

        binding.btnSend.setOnClickListener { sendMessage() }

        binding.etMessage.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage(); true
            } else false
        }

        // Suggested prompts
        binding.chipSuggest1.setOnClickListener { sendPredefined("Can you suggest a workout plan for my fitness goal?") }
        binding.chipSuggest2.setOnClickListener { sendPredefined("What are some healthy meal ideas for my daily goal?") }
        binding.chipSuggest3.setOnClickListener { sendPredefined("Can you analyze my progress and give me tips to improve?") }

        binding.btnClearChat.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Clear Chat")
                .setMessage("Clear all chat history?")
                .setPositiveButton("Clear") { _, _ -> viewModel.clearHistory() }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun sendMessage() {
        val text = binding.etMessage.text?.toString()?.trim() ?: return
        if (text.isBlank()) return
        binding.etMessage.text?.clear()
        viewModel.sendMessage(text)
    }

    private fun sendPredefined(text: String) {
        binding.etMessage.setText(text)
        sendMessage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
