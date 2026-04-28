package com.nhom10.aifitnutrition.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.data.model.WaterLog
import com.nhom10.aifitnutrition.data.repository.NutritionRepository
import com.nhom10.aifitnutrition.databinding.FragmentDashboardBinding
import com.nhom10.aifitnutrition.util.DateUtils
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.util.StreakManager
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels {
        DashboardViewModel.Factory(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Greeting and date
        binding.tvGreeting.text = "${DateUtils.getGreeting()},"
        binding.tvDate.text = DateUtils.formatDisplay(viewModel.today)

        setupObservers()
        setupChart()
        setupButtons()
        
        // Initial load of streak
        binding.tvStreak.text = "${StreakManager.getStreak(requireContext())} 🔥"
    }

    private fun setupObservers() {
        viewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            profile?.let {
                binding.tvUserName.text = it.name.ifEmpty { "Welcome! 👋" }
                val goal = viewModel.getCalorieGoal(it)
                updateCalories(
                    viewModel.caloriesEaten.value ?: 0,
                    viewModel.caloriesBurned.value ?: 0,
                    goal
                )
                updateMacros(
                    viewModel.proteinEaten.value ?: 0f, it.proteinGoalG,
                    viewModel.carbsEaten.value ?: 0f, it.carbsGoalG,
                    viewModel.fatEaten.value ?: 0f, it.fatGoalG
                )
                binding.tvWaterGoal.text = "Goal: ${it.waterGoalMl} ml"
                val waterPct = ((viewModel.waterConsumedMl.value ?: 0) * 100 / it.waterGoalMl).coerceIn(0, 100)
                binding.pbWater.progress = waterPct
            }
        }

        viewModel.caloriesEaten.observe(viewLifecycleOwner) { eaten ->
            val burned = viewModel.caloriesBurned.value ?: 0
            val goal = viewModel.userProfile.value?.let { viewModel.getCalorieGoal(it) } ?: 2000
            updateCalories(eaten, burned, goal)
            binding.tvCaloriesEaten.text = eaten.toString()
            checkStreak()
        }

        viewModel.caloriesBurned.observe(viewLifecycleOwner) { burned ->
            val eaten = viewModel.caloriesEaten.value ?: 0
            val goal = viewModel.userProfile.value?.let { viewModel.getCalorieGoal(it) } ?: 2000
            updateCalories(eaten, burned, goal)
            binding.tvCaloriesBurned.text = burned.toString()
            checkStreak()
        }

        viewModel.proteinEaten.observe(viewLifecycleOwner) { protein ->
            val goal = viewModel.userProfile.value?.proteinGoalG ?: 150f
            binding.tvProteinVal.text = "${protein.toInt()}g"
            binding.pbProtein.progress = ((protein / goal) * 100).toInt().coerceIn(0, 100)
        }

        viewModel.carbsEaten.observe(viewLifecycleOwner) { carbs ->
            val goal = viewModel.userProfile.value?.carbsGoalG ?: 200f
            binding.tvCarbsVal.text = "${carbs.toInt()}g"
            binding.pbCarbs.progress = ((carbs / goal) * 100).toInt().coerceIn(0, 100)
        }

        viewModel.fatEaten.observe(viewLifecycleOwner) { fat ->
            val goal = viewModel.userProfile.value?.fatGoalG ?: 65f
            binding.tvFatVal.text = "${fat.toInt()}g"
            binding.pbFat.progress = ((fat / goal) * 100).toInt().coerceIn(0, 100)
        }

        viewModel.waterConsumedMl.observe(viewLifecycleOwner) { water ->
            binding.tvWaterAmount.text = "$water ml"
            val goal = viewModel.userProfile.value?.waterGoalMl ?: 2500
            binding.pbWater.progress = ((water * 100) / goal).coerceIn(0, 100)
            checkStreak()
        }

        viewModel.weeklyCalories.observe(viewLifecycleOwner) { weekly ->
            updateChart(weekly)
        }
    }

    private fun checkStreak() {
        val water = viewModel.waterConsumedMl.value ?: 0
        val eaten = viewModel.caloriesEaten.value ?: 0
        val burned = viewModel.caloriesBurned.value ?: 0
        
        if (water > 0 || eaten > 0 || burned > 0) {
            val justExtended = StreakManager.extendStreak(requireContext())
            if (justExtended) {
                showConfetti()
            }
        }
        binding.tvStreak.text = "${StreakManager.getStreak(requireContext())} 🔥"
    }

    private fun showConfetti() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def, 0x00D4AA),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        binding.konfettiView.start(party)
    }

    private fun updateCalories(eaten: Int, burned: Int, goal: Int) {
        val remaining = viewModel.getRemainingCalories(goal, eaten, burned)
        binding.tvNetCalories.text = remaining.toString()
        val color = when {
            remaining < 0 -> ContextCompat.getColor(requireContext(), R.color.error)
            remaining < 200 -> ContextCompat.getColor(requireContext(), R.color.warning)
            else -> ContextCompat.getColor(requireContext(), R.color.accent_teal)
        }
        binding.tvNetCalories.setTextColor(color)
    }

    private fun updateMacros(p: Float, pg: Float, c: Float, cg: Float, f: Float, fg: Float) {
        binding.tvProteinVal.text = "${p.toInt()}g"
        binding.pbProtein.progress = ((p / pg.coerceAtLeast(1f)) * 100).toInt().coerceIn(0, 100)
        binding.tvCarbsVal.text = "${c.toInt()}g"
        binding.pbCarbs.progress = ((c / cg.coerceAtLeast(1f)) * 100).toInt().coerceIn(0, 100)
        binding.tvFatVal.text = "${f.toInt()}g"
        binding.pbFat.progress = ((f / fg.coerceAtLeast(1f)) * 100).toInt().coerceIn(0, 100)
    }

    private fun setupChart() {
        val chart = binding.barChart
        chart.apply {
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(false)
            setDrawGridBackground(false)
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                textColor = ContextCompat.getColor(requireContext(), R.color.text_tertiary)
                textSize = 10f
            }
            axisLeft.apply {
                setDrawGridLines(true)
                gridColor = ContextCompat.getColor(requireContext(), R.color.bg_card_elevated)
                textColor = ContextCompat.getColor(requireContext(), R.color.text_tertiary)
                textSize = 10f
            }
            axisRight.isEnabled = false
            animateY(800)
        }
    }

    private fun updateChart(data: List<com.nhom10.aifitnutrition.data.dao.DateCalories>) {
        val labels = DateUtils.getLast7Days().map { DateUtils.formatShort(it) }
        val map = data.associate { it.date to it.totalCals }
        val entries = DateUtils.getLast7Days().mapIndexed { i, date ->
            BarEntry(i.toFloat(), (map[date] ?: 0).toFloat())
        }
        val dataSet = BarDataSet(entries, "Calories").apply {
            color = ContextCompat.getColor(requireContext(), R.color.accent_teal)
            setDrawValues(false)
        }
        binding.barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        binding.barChart.data = BarData(dataSet)
        binding.barChart.invalidate()
    }

    private fun setupButtons() {
        binding.btnAddWater.setOnClickListener {
            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(requireContext())
                val repo = NutritionRepository(db.foodLogDao(), db.waterLogDao(), db.userProfileDao())
                repo.insertWaterLog(WaterLog(amountMl = 250, date = DateUtils.today()))
            }
        }
        binding.btnLogFood.setOnClickListener {
            findNavController().navigate(R.id.foodLogFragment)
        }
        binding.btnLogWorkout.setOnClickListener {
            findNavController().navigate(R.id.workoutFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
