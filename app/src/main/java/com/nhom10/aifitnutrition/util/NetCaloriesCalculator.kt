package com.nhom10.aifitnutrition.util

import com.nhom10.aifitnutrition.data.model.UserProfile
import kotlin.math.roundToInt

object NetCaloriesCalculator {

    /**
     * Calculate BMR using Mifflin-St Jeor equation
     */
    fun calculateBMR(profile: UserProfile): Float {
        return if (profile.gender == "male") {
            (10 * profile.weightKg + 6.25f * profile.heightCm - 5 * profile.ageYears + 5)
        } else {
            (10 * profile.weightKg + 6.25f * profile.heightCm - 5 * profile.ageYears - 161)
        }
    }

    /**
     * Calculate TDEE (Total Daily Energy Expenditure)
     */
    fun calculateTDEE(profile: UserProfile): Float {
        val bmr = calculateBMR(profile)
        val multiplier = when (profile.activityLevel) {
            "sedentary"   -> 1.2f
            "light"       -> 1.375f
            "moderate"    -> 1.55f
            "active"      -> 1.725f
            "very_active" -> 1.9f
            else          -> 1.55f
        }
        return bmr * multiplier
    }

    /**
     * Calculate recommended daily calorie goal based on user's goal
     */
    fun calculateCalorieGoal(profile: UserProfile): Int {
        val tdee = calculateTDEE(profile)
        return when (profile.goalType) {
            "lose"     -> (tdee - 500).roundToInt()
            "gain"     -> (tdee + 300).roundToInt()
            "maintain" -> tdee.roundToInt()
            else       -> tdee.roundToInt()
        }
    }

    /**
     * Net calories = calories eaten - calories burned through exercise
     */
    fun calculateNetCalories(eaten: Int, burned: Int): Int = eaten - burned

    /**
     * Remaining calories = goal - net calories
     */
    fun calculateRemainingCalories(goal: Int, eaten: Int, burned: Int): Int =
        goal - calculateNetCalories(eaten, burned)

    /**
     * Calculate estimated calories burned for workout
     */
    fun estimateCaloriesBurned(
        workoutType: String,
        durationMinutes: Int,
        weightKg: Float,
        intensity: String
    ): Int {
        val metValue = when (workoutType.lowercase()) {
            "running"   -> if (intensity == "Intense") 11.0f else 8.0f
            "cycling"   -> if (intensity == "Intense") 10.0f else 7.5f
            "walking"   -> 3.5f
            "swimming"  -> 8.0f
            "gym"       -> if (intensity == "Intense") 6.0f else 4.0f
            "yoga"      -> 2.5f
            "hiit"      -> 10.0f
            else        -> 5.0f
        }
        return ((metValue * weightKg * durationMinutes) / 60).roundToInt()
    }
}
