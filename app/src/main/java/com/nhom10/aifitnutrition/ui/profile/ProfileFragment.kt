package com.nhom10.aifitnutrition.ui.profile

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.data.model.UserProfile
import com.nhom10.aifitnutrition.databinding.FragmentProfileBinding
import com.nhom10.aifitnutrition.util.PdfExporter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModel.Factory(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupButtons()
        setupWeightChart()
    }

    private fun setupObservers() {
        viewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            profile ?: return@observe
            binding.tvProfileName.text = profile.name.ifEmpty { getString(R.string.your_name_default) }
            val goalLabel = when (profile.goalType) {
                "lose" -> getString(R.string.profile_goal_lose)
                "gain" -> getString(R.string.profile_goal_gain)
                else -> getString(R.string.profile_goal_maintain)
            }
            binding.tvProfileGoal.text = getString(R.string.goal_format, goalLabel)
            binding.tvProfileCalGoal.text = profile.dailyCalorieGoal.toString()
            binding.tvStatWeight.text = profile.weightKg.toString()
            binding.tvStatHeight.text = profile.heightCm.toInt().toString()
            val bmi = profile.weightKg / ((profile.heightCm / 100f) * (profile.heightCm / 100f))
            binding.tvStatBmi.text = String.format("%.1f", bmi)
        }

        viewModel.recentWeightHistory.observe(viewLifecycleOwner) { logs ->
            if (logs.isNotEmpty()) {
                val entries = logs.mapIndexed { i, log ->
                    Entry(i.toFloat(), log.weightKg)
                }
                val dataSet = LineDataSet(entries, "Weight").apply {
                    color = ContextCompat.getColor(requireContext(), R.color.accent_violet)
                    setDrawCircles(true)
                    circleRadius = 4f
                    circleHoleColor = ContextCompat.getColor(requireContext(), R.color.bg_card)
                    setCircleColor(ContextCompat.getColor(requireContext(), R.color.accent_violet))
                    setDrawValues(false)
                    lineWidth = 2.5f
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                }
                binding.weightChart.data = LineData(dataSet)
                binding.weightChart.invalidate()
            }
        }

        viewModel.snackbarMessage.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrEmpty()) {
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
                viewModel.clearSnackbar()
            }
        }
    }

    private fun setupWeightChart() {
        binding.weightChart.apply {
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(false)
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                textColor = ContextCompat.getColor(requireContext(), R.color.text_tertiary)
                textSize = 9f
            }
            axisLeft.apply {
                setDrawGridLines(true)
                gridColor = ContextCompat.getColor(requireContext(), R.color.bg_card_elevated)
                textColor = ContextCompat.getColor(requireContext(), R.color.text_tertiary)
            }
            axisRight.isEnabled = false
            animateX(600)
        }
    }

    private fun setupButtons() {
        binding.btnLogWeight.setOnClickListener {
            val weightStr = binding.etWeight.text?.toString()?.trim()
            val weight = weightStr?.toFloatOrNull()
            if (weight != null && weight > 0) {
                viewModel.logWeight(weight)
                binding.etWeight.text?.clear()
            } else {
                Snackbar.make(binding.root, getString(R.string.snackbar_enter_valid_weight), Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnExportPdf.setOnClickListener {
            val profile = viewModel.userProfile.value
            if (profile == null) {
                Snackbar.make(binding.root, getString(R.string.snackbar_no_profile_to_export), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val pdfFile = PdfExporter.exportHealthReport(requireContext(), profile)
            if (pdfFile != null) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.snackbar_pdf_saved, pdfFile.name),
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Snackbar.make(binding.root, getString(R.string.snackbar_pdf_export_failed), Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnEditProfile.setOnClickListener {
            showEditProfileDialog()
        }

        binding.btnLanguage.setOnClickListener {
            showLanguageDialog()
        }
    }

    /**
     * Show language selection dialog. Supports EN / VI.
     * Uses AppCompatDelegate.setApplicationLocales() for API 33+ (per-app language)
     * Falls back to Activity recreation with Locale for older APIs.
     */
    private fun showLanguageDialog() {
        val languages = arrayOf(
            "\uD83C\uDDFA\uD83C\uDDF8 ${getString(R.string.lang_english)}",
            "\uD83C\uDDFB\uD83C\uDDF3 ${getString(R.string.lang_vietnamese)}"
        )
        val langCodes = arrayOf("en", "vi")

        // Đọc ngôn ngữ đang dùng từ SharedPrefs
        val currentLang = com.nhom10.aifitnutrition.App.getSavedLanguage(requireContext())
        val currentIndex = langCodes.indexOf(currentLang).takeIf { it >= 0 } ?: 0

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_language_title))
            .setSingleChoiceItems(languages, currentIndex) { dialog, which ->
                dialog.dismiss()
                applyLanguage(langCodes[which])
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun applyLanguage(langCode: String) {
        // 1. Lưu lựa chọn vào SharedPreferences để áp dụng lại khi mở app
        com.nhom10.aifitnutrition.App.saveLanguage(requireContext(), langCode)
        // 2. Áp dụng locale ngay lập tức qua AppCompatDelegate (hoạt động với tất cả API)
        com.nhom10.aifitnutrition.App.applyLocale(langCode)
        // 3. Recreate activity để áp dụng ngay
        requireActivity().recreate()
    }

    private fun showEditProfileDialog() {
        val profile = viewModel.userProfile.value ?: UserProfile()
        val goals = arrayOf(
            getString(R.string.profile_goal_lose),
            getString(R.string.profile_goal_gain),
            getString(R.string.profile_goal_maintain)
        )
        val goalKeys = arrayOf("lose", "gain", "maintain")
        var selectedGoal = goalKeys.indexOf(profile.goalType)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_edit_goal))
            .setSingleChoiceItems(goals, selectedGoal) { _, which -> selectedGoal = which }
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                val updatedProfile = profile.copy(goalType = goalKeys[selectedGoal])
                viewModel.updateProfile(updatedProfile)
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
