package com.nhom10.aifitnutrition.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nhom10.aifitnutrition.data.model.WorkoutLog
import com.nhom10.aifitnutrition.databinding.ItemWorkoutBinding

class WorkoutAdapter(
    private val onDelete: (WorkoutLog) -> Unit
) : ListAdapter<WorkoutLog, WorkoutAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(val binding: ItemWorkoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val emojiMap = mapOf(
        "running" to "🏃", "cycling" to "🚴", "gym" to "💪",
        "walking" to "🚶", "swimming" to "🏊", "yoga" to "🧘",
        "hiit" to "⚡"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWorkoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvWorkoutEmoji.text = emojiMap[item.workoutType.lowercase()] ?: "🏋️"
            tvWorkoutType.text = item.workoutType
            tvWorkoutDetails.text = "${item.durationMinutes} min • ${item.intensity}"
            tvWorkoutCalories.text = item.caloriesBurned.toString()
            btnDeleteWorkout.setOnClickListener { onDelete(item) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<WorkoutLog>() {
            override fun areItemsTheSame(a: WorkoutLog, b: WorkoutLog) = a.id == b.id
            override fun areContentsTheSame(a: WorkoutLog, b: WorkoutLog) = a == b
        }
    }
}
