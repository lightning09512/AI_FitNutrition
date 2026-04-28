package com.nhom10.aifitnutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_logs")
data class FoodLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mealType: String,        // breakfast, lunch, dinner, snack
    val foodName: String,
    val calories: Int,
    val proteinG: Float,
    val carbsG: Float,
    val fatG: Float,
    val portionGrams: Float = 100f,
    val imageUri: String? = null,
    val date: String,            // ISO date "2026-04-08"
    val timestamp: Long = System.currentTimeMillis(),
    val isAiAnalyzed: Boolean = false
)
