package com.nhom10.aifitnutrition.ui.workout

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.databinding.FragmentWorkoutPlayerBinding

class WorkoutPlayerFragment : Fragment() {

    private var _binding: FragmentWorkoutPlayerBinding? = null
    private val binding get() = _binding!!

    private var timer: CountDownTimer? = null
    private var isPaused = false
    private var currentIndex = 0
    private var remainingSec = 0

    private var program: WorkoutProgram? = null
    private val exercises get() = program?.exercises.orEmpty()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val programId = arguments?.getString("programId").orEmpty()
        program = WorkoutPrograms.findProgramById(programId)

        if (program == null || exercises.isEmpty()) {
            Snackbar.make(binding.root, "Khong tim thay chuong trinh!", Snackbar.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }

        binding.tvPlayerProgramTitle.text = program?.title
        bindExercise(index = 0, restart = true)
        setupActions()
    }

    private fun setupActions() {
        binding.btnPrevExercise.setOnClickListener {
            if (currentIndex > 0) bindExercise(currentIndex - 1, restart = true)
        }
        binding.btnNextExercise.setOnClickListener {
            if (currentIndex < exercises.lastIndex) {
                bindExercise(currentIndex + 1, restart = true)
            } else {
                finishWorkout()
            }
        }
        binding.btnPauseResume.setOnClickListener {
            if (isPaused) resumeTimer() else pauseTimer()
        }
        binding.btnFinishWorkout.setOnClickListener {
            finishWorkout()
        }
    }

    private fun bindExercise(index: Int, restart: Boolean) {
        currentIndex = index
        val exercise = exercises[index]
        val duration = exercise.durationSeconds.coerceAtLeast(10)

        binding.tvPlayerStep.text = "Bai ${index + 1}/${exercises.size}"
        binding.tvExerciseName.text = exercise.name
        binding.tvExerciseTarget.text = exercise.target
        binding.progressTimer.max = duration

        loadExerciseImage(exercise)

        if (restart) startTimer(duration)
    }

    private fun loadExerciseImage(exercise: WorkoutExercise) {
        val mediaPath = exercise.mediaPath.trim()

        // Hien placeholder mac dinh truoc
        binding.layoutExercisePlaceholder.visibility = View.VISIBLE
        binding.ivExerciseMedia.setImageDrawable(null)

        if (mediaPath.isBlank()) return

        val source: Any = if (mediaPath.startsWith("http")) {
            mediaPath
        } else {
            "file:///android_asset/$mediaPath"
        }

        Glide.with(this)
            .load(source)
            .placeholder(R.drawable.ic_exercise_placeholder)
            .error(R.drawable.ic_exercise_placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    if (_binding != null) binding.layoutExercisePlaceholder.visibility = View.VISIBLE
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    if (_binding != null) binding.layoutExercisePlaceholder.visibility = View.GONE
                    return false
                }
            })
            .into(binding.ivExerciseMedia)
    }

    private fun startTimer(seconds: Int) {
        timer?.cancel()
        isPaused = false
        binding.btnPauseResume.text = "Tam dung"
        remainingSec = seconds
        binding.progressTimer.progress = seconds
        updateTimerLabel(remainingSec)

        timer = object : CountDownTimer(seconds * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                if (_binding == null) return
                remainingSec = (millisUntilFinished / 1000L).toInt().coerceAtLeast(0)
                binding.progressTimer.progress = remainingSec
                updateTimerLabel(remainingSec)
            }
            override fun onFinish() {
                if (_binding == null) return
                binding.progressTimer.progress = 0
                updateTimerLabel(0)
                if (currentIndex < exercises.lastIndex) {
                    bindExercise(currentIndex + 1, restart = true)
                } else {
                    finishWorkout()
                }
            }
        }.start()
    }

    private fun pauseTimer() {
        timer?.cancel()
        isPaused = true
        binding.btnPauseResume.text = "Tiep tuc"
    }

    private fun resumeTimer() {
        startTimer(remainingSec.coerceAtLeast(1))
    }

    private fun updateTimerLabel(seconds: Int) {
        binding.tvExerciseTimer.text = String.format("%02d:%02d", seconds / 60, seconds % 60)
    }

    private fun finishWorkout() {
        Snackbar.make(binding.root, "Hoan thanh bai tap! Tuyet voi! \uD83D\uDCAA", Snackbar.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        timer?.cancel()
        timer = null
        _binding = null
        super.onDestroyView()
    }
}