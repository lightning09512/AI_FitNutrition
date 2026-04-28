package com.nhom10.aifitnutrition.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.data.model.WorkoutLog
import com.nhom10.aifitnutrition.databinding.FragmentWorkoutBinding
import com.nhom10.aifitnutrition.ui.adapter.WorkoutAdapter
import com.nhom10.aifitnutrition.ui.adapter.WorkoutProgramAdapter
import com.nhom10.aifitnutrition.util.DateUtils

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutViewModel by viewModels {
        WorkoutViewModel.Factory(requireActivity().application)
    }

    private val workoutLogAdapter = WorkoutAdapter { workout ->
        viewModel.deleteWorkout(workout)
    }
    private lateinit var programAdapter: WorkoutProgramAdapter

    private var selectedWorkoutType = "Running"
    private var selectedIntensity = "Moderate"
    private var selectedProgram: WorkoutProgram? = null
    private var loadedPrograms: List<WorkoutProgram> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        programAdapter = WorkoutProgramAdapter { program ->
            selectedProgram = program
            programAdapter.setSelected(program.id)
            applyProgramToInputs(program)
            openProgramPlayer(program)
        }

        binding.rvWorkouts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWorkouts.adapter = workoutLogAdapter
        binding.rvWorkoutPrograms.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvWorkoutPrograms.adapter = programAdapter

        setupPrograms()
        setupChips()
        setupObservers()
        setupSlider()
    }

    private fun setupPrograms() {
        val libraryItems = ExerciseLibraryLoader.loadFromAssets(requireContext())
        loadedPrograms = WorkoutPrograms.fromExerciseLibrary(libraryItems)
        WorkoutPrograms.setDynamicPlans(loadedPrograms)

        programAdapter.submitList(loadedPrograms)
        val defaultProgram = loadedPrograms.firstOrNull() ?: return
        selectedProgram = defaultProgram
        programAdapter.setSelected(defaultProgram.id)
        applyProgramToInputs(defaultProgram)

        binding.btnStartProgram.visibility = View.GONE
    }

    private fun setupChips() {
        binding.chipRunning.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedWorkoutType = "Running"; updateEstimate() }
        }
        binding.chipCycling.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedWorkoutType = "Cycling"; updateEstimate() }
        }
        binding.chipGym.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedWorkoutType = "Gym"; updateEstimate() }
        }
        binding.chipWalking.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedWorkoutType = "Walking"; updateEstimate() }
        }
        binding.chipSwimming.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedWorkoutType = "Swimming"; updateEstimate() }
        }
        binding.chipYoga.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedWorkoutType = "Yoga"; updateEstimate() }
        }
        binding.chipHiit.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedWorkoutType = "HIIT"; updateEstimate() }
        }
        binding.chipRunning.isChecked = true

        binding.chipLight.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedIntensity = "Light"; updateEstimate() }
        }
        binding.chipModerate.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedIntensity = "Moderate"; updateEstimate() }
        }
        binding.chipIntense.setOnCheckedChangeListener { _, checked ->
            if (checked) { selectedIntensity = "Intense"; updateEstimate() }
        }

        binding.btnLogWorkout.setOnClickListener {
            val duration = binding.sliderDuration.value.toInt()
            val weight = viewModel.userProfile.value?.weightKg ?: 70f
            val burned = viewModel.estimateCalories(
                selectedWorkoutType, duration, selectedIntensity, weight
            )
            viewModel.insertWorkout(
                WorkoutLog(
                    workoutType = selectedWorkoutType,
                    durationMinutes = duration,
                    caloriesBurned = burned,
                    intensity = selectedIntensity,
                    date = DateUtils.today()
                )
            )
        }
    }

    private fun setupSlider() {
        binding.sliderDuration.addOnChangeListener { _, value, _ ->
            binding.tvDurationVal.text = "${value.toInt()} min"
            updateEstimate()
        }
    }

    private fun applyProgramToInputs(program: WorkoutProgram) {
        selectedWorkoutType = program.workoutType
        selectedIntensity = program.intensity
        binding.sliderDuration.value = program.durationMinutes.toFloat()
        binding.tvDurationVal.text = "${program.durationMinutes} min"
        setWorkoutChip(program.workoutType)
        setIntensityChip(program.intensity)
        updateEstimate()
    }

    private fun setWorkoutChip(type: String) {
        when (type) {
            "Running"  -> binding.chipRunning.isChecked  = true
            "Cycling"  -> binding.chipCycling.isChecked  = true
            "Gym"      -> binding.chipGym.isChecked      = true
            "Walking"  -> binding.chipWalking.isChecked  = true
            "Swimming" -> binding.chipSwimming.isChecked = true
            "Yoga"     -> binding.chipYoga.isChecked     = true
            "HIIT"     -> binding.chipHiit.isChecked     = true
        }
    }

    private fun setIntensityChip(intensity: String) {
        when (intensity) {
            "Light"    -> binding.chipLight.isChecked    = true
            "Moderate" -> binding.chipModerate.isChecked = true
            "Intense"  -> binding.chipIntense.isChecked  = true
        }
    }

    private fun openProgramPlayer(program: WorkoutProgram) {
        val args = Bundle().apply { putString("programId", program.id) }
        findNavController().navigate(R.id.action_workout_to_player, args)
    }

    private fun updateEstimate() {
        val duration = binding.sliderDuration.value.toInt()
        val weight = viewModel.userProfile.value?.weightKg ?: 70f
        val est = viewModel.estimateCalories(
            selectedWorkoutType, duration, selectedIntensity, weight
        )
        binding.tvEstimatedBurn.text = "~$est kcal"
    }

    private fun setupObservers() {
        viewModel.workoutsToday.observe(viewLifecycleOwner) { workouts ->
            workoutLogAdapter.submitList(workouts)
            binding.tvNoWorkouts.visibility =
                if (workouts.isEmpty()) View.VISIBLE else View.GONE
        }
        viewModel.totalCaloriesBurnedToday.observe(viewLifecycleOwner) { burned ->
            binding.tvTotalBurned.text = "\uD83D\uDD25 ${burned ?: 0} kcal"
        }
        viewModel.snackbarMessage.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrEmpty()) {
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
                viewModel.clearSnackbar()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}