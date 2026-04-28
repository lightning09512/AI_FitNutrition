package com.nhom10.aifitnutrition.ui.chat

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhom10.aifitnutrition.R
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
        binding.chipSuggest1.setOnClickListener { sendPredefined("Hãy gợi ý cho tôi một kế hoạch tập luyện phù hợp với mục tiêu hiện tại.") }
        binding.chipSuggest2.setOnClickListener { sendPredefined("Hãy gợi ý cho tôi các bữa ăn lành mạnh phù hợp với mục tiêu hằng ngày.") }
        binding.chipSuggest3.setOnClickListener { sendPredefined("Hãy phân tích tiến độ hiện tại và cho tôi lời khuyên để cải thiện.") }

        binding.btnClearChat.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.chat_clear_title))
                .setMessage(getString(R.string.chat_clear_message))
                .setPositiveButton(getString(R.string.chat_clear)) { _, _ -> viewModel.clearHistory() }
                .setNegativeButton(getString(R.string.cancel), null)
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
