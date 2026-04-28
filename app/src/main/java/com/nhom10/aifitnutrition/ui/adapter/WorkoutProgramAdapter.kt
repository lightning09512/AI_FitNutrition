package com.nhom10.aifitnutrition.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhom10.aifitnutrition.databinding.ItemWorkoutProgramBinding
import com.nhom10.aifitnutrition.ui.workout.WorkoutProgram

class WorkoutProgramAdapter(
    private val onClick: (WorkoutProgram) -> Unit
) : ListAdapter<WorkoutProgram, WorkoutProgramAdapter.ViewHolder>(DIFF) {

    private var selectedId: String? = null

    inner class ViewHolder(val binding: ItemWorkoutProgramBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWorkoutProgramBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvProgramTitle.text = item.title
            tvProgramSubtitle.text = item.subtitle
            tvProgramMeta.text = "${item.durationMinutes} min • ${item.intensity}"

            val isSelected = item.id == selectedId
            root.strokeWidth = if (isSelected) 3 else 1
            root.alpha = if (isSelected) 1f else 0.9f

            // Load image for the first exercise in the program
            val firstExercise = item.exercises.firstOrNull()
            if (firstExercise != null && firstExercise.mediaPath.isNotBlank()) {
                Glide.with(holder.itemView.context)
                    .load(firstExercise.mediaPath)
                    .placeholder(com.nhom10.aifitnutrition.R.drawable.ic_workout)
                    .error(com.nhom10.aifitnutrition.R.drawable.ic_workout)
                    .centerCrop()
                    .into(ivProgramImage)
            } else {
                ivProgramImage.setImageResource(com.nhom10.aifitnutrition.R.drawable.ic_workout)
            }

            root.setOnClickListener {
                selectedId = item.id
                notifyDataSetChanged()
                onClick(item)
            }
        }
    }

    fun setSelected(programId: String?) {
        selectedId = programId
        notifyDataSetChanged()
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<WorkoutProgram>() {
            override fun areItemsTheSame(oldItem: WorkoutProgram, newItem: WorkoutProgram): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WorkoutProgram, newItem: WorkoutProgram): Boolean =
                oldItem == newItem
        }
    }
}
