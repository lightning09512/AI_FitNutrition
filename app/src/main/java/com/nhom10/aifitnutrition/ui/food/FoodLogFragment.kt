package com.nhom10.aifitnutrition.ui.food

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.data.model.FoodLog
import com.nhom10.aifitnutrition.databinding.FragmentFoodLogBinding
import com.nhom10.aifitnutrition.ui.adapter.FoodLogAdapter
import com.nhom10.aifitnutrition.util.DateUtils
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

class FoodLogFragment : Fragment() {

    private var _binding: FragmentFoodLogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodViewModel by viewModels {
        FoodViewModel.Factory(requireActivity().application)
    }

    private val adapter = FoodLogAdapter { food -> viewModel.deleteFoodLog(food) }
    private var currentMeal = "breakfast"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFoodLogs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFoodLogs.adapter = adapter

        // Set up tabs
        val meals = listOf("breakfast", "lunch", "dinner", "snack")
        val labels = listOf("🌅 Breakfast", "☀️ Lunch", "🌙 Dinner", "🍎 Snack")
        labels.forEachIndexed { i, label ->
            val tab = binding.tabLayout.newTab().setText(label)
            binding.tabLayout.addTab(tab)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentMeal = meals[tab?.position ?: 0]
                observeMealData()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        observeMealData()
        setupFab()

        viewModel.snackbarMessage.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrEmpty()) {
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
                viewModel.clearSnackbar()
            }
        }

        // Observe total calories
        viewModel.run {
            val cal = listOf(breakfastLogs, lunchLogs, dinnerLogs, snackLogs)
            cal.forEach { liveData ->
                liveData.observe(viewLifecycleOwner) {
                    val total = (breakfastLogs.value?.sumOf { it.calories } ?: 0) +
                            (lunchLogs.value?.sumOf { it.calories } ?: 0) +
                            (dinnerLogs.value?.sumOf { it.calories } ?: 0) +
                            (snackLogs.value?.sumOf { it.calories } ?: 0)
                    binding.tvTotalCaloriesToday.text = "$total kcal"
                }
            }
        }
    }

    private fun observeMealData() {
        val liveData = when (currentMeal) {
            "breakfast" -> viewModel.breakfastLogs
            "lunch" -> viewModel.lunchLogs
            "dinner" -> viewModel.dinnerLogs
            else -> viewModel.snackLogs
        }
        liveData.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.tvEmptyMeal.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun setupFab() {
        binding.fabAddFood.setOnClickListener {
            showAddFoodDialog()
        }
        binding.fabAddFood.setOnLongClickListener {
            findNavController().navigate(R.id.action_food_to_camera)
            true
        }
    }

    private fun showAddFoodDialog() {
        val options = arrayOf("📷 Scan with AI Camera", "✏️ Manual Entry")
        AlertDialog.Builder(requireContext())
            .setTitle("Add Food")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> findNavController().navigate(R.id.action_food_to_camera)
                    1 -> showManualEntryDialog()
                }
            }.show()
    }

    private fun showManualEntryDialog() {
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 24, 48, 0)
        }
        val fields = listOf("Food Name", "Calories", "Protein (g)", "Carbs (g)", "Fat (g)").map {
            EditText(requireContext()).apply {
                hint = it
                setTextColor(resources.getColor(R.color.text_primary, null))
                setHintTextColor(resources.getColor(R.color.text_tertiary, null))
                layout.addView(this)
            }
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Manual Food Entry")
            .setView(layout)
            .setPositiveButton("Log") { _, _ ->
                val name = fields[0].text.toString().ifEmpty { "Food" }
                val cal = fields[1].text.toString().toIntOrNull() ?: 0
                val protein = fields[2].text.toString().toFloatOrNull() ?: 0f
                val carbs = fields[3].text.toString().toFloatOrNull() ?: 0f
                val fat = fields[4].text.toString().toFloatOrNull() ?: 0f
                viewModel.insertFoodLog(
                    FoodLog(
                        mealType = currentMeal,
                        foodName = name,
                        calories = cal,
                        proteinG = protein,
                        carbsG = carbs,
                        fatG = fat,
                        date = DateUtils.today()
                    )
                )
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
