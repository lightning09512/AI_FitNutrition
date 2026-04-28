package com.nhom10.aifitnutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey
    val id: Int = 1,  // Single user
    val name: String = "",
    val ageYears: Int = 25,
    val heightCm: Float = 170f,
    val weightKg: Float = 70f,
    val goalType: String = "maintain",  // lose | gain | maintain
    val targetWeightKg: Float = 70f,
    val dailyCalorieGoal: Int = 2000,
    val proteinGoalG: Float = 150f,
    val carbsGoalG: Float = 200f,
    val fatGoalG: Float = 65f,
    val waterGoalMl: Int = 2500,
    val activityLevel: String = "moderate",  // sedentary | light | moderate | active | very_active
    val gender: String = "male"
)
