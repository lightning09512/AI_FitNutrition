package com.nhom10.aifitnutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_logs")
data class WorkoutLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workoutType: String,     // Running, Walking, Cycling, Swimming, Gym, Yoga, etc.
    val durationMinutes: Int,
    val caloriesBurned: Int,
    val intensity: String = "Moderate",  // Light, Moderate, Intense
    val notes: String? = null,
    val date: String,
    val timestamp: Long = System.currentTimeMillis()
)
