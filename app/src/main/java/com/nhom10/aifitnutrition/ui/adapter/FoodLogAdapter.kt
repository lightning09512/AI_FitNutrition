package com.nhom10.aifitnutrition.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nhom10.aifitnutrition.data.model.FoodLog
import com.nhom10.aifitnutrition.databinding.ItemFoodLogBinding
import com.bumptech.glide.Glide

class FoodLogAdapter(
    private val onDelete: (FoodLog) -> Unit
) : ListAdapter<FoodLog, FoodLogAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(val binding: ItemFoodLogBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodLogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvFoodName.text = item.foodName
            tvFoodCalories.text = item.calories.toString()
            tvFoodMacros.text = "P: ${item.proteinG.toInt()}g • C: ${item.carbsG.toInt()}g • F: ${item.fatG.toInt()}g"
            tvAiBadge.visibility = if (item.isAiAnalyzed) View.VISIBLE else View.GONE
            if (!item.imageUri.isNullOrEmpty()) {
                Glide.with(ivFoodImage.context)
                    .load(item.imageUri)
                    .centerCrop()
                    .into(ivFoodImage)
            } else {
                ivFoodImage.setImageResource(com.nhom10.aifitnutrition.R.drawable.ic_food)
                ivFoodImage.setPadding(12, 12, 12, 12)
            }
            btnDeleteFood.setOnClickListener { onDelete(item) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<FoodLog>() {
            override fun areItemsTheSame(a: FoodLog, b: FoodLog) = a.id == b.id
            override fun areContentsTheSame(a: FoodLog, b: FoodLog) = a == b
        }
    }
}
