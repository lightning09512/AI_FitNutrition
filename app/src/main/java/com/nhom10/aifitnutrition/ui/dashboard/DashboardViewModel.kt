package com.nhom10.aifitnutrition.ui.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.data.model.UserProfile
import com.nhom10.aifitnutrition.data.repository.FitnessRepository
import com.nhom10.aifitnutrition.data.repository.NutritionRepository
import com.nhom10.aifitnutrition.util.DateUtils
import com.nhom10.aifitnutrition.util.NetCaloriesCalculator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val nutritionRepo = NutritionRepository(
        db.foodLogDao(), db.waterLogDao(), db.userProfileDao()
    )
    private val fitnessRepo = FitnessRepository(db.workoutLogDao(), db.weightLogDao())

    val today = DateUtils.today()

    val userProfile: LiveData<UserProfile?> =
        nutritionRepo.getUserProfile().asLiveData()

    val caloriesEaten: LiveData<Int> =
        nutritionRepo.getTotalCaloriesForDate(today)
            .map { it ?: 0 }.asLiveData()

    val caloriesBurned: LiveData<Int> =
        fitnessRepo.getTotalCaloriesBurnedForDate(today)
            .map { it ?: 0 }.asLiveData()

    val proteinEaten: LiveData<Float> =
        nutritionRepo.getTotalProteinForDate(today)
            .map { it ?: 0f }.asLiveData()

    val carbsEaten: LiveData<Float> =
        nutritionRepo.getTotalCarbsForDate(today)
            .map { it ?: 0f }.asLiveData()

    val fatEaten: LiveData<Float> =
        nutritionRepo.getTotalFatForDate(today)
            .map { it ?: 0f }.asLiveData()

    val waterConsumedMl: LiveData<Int> =
        nutritionRepo.getTotalWaterForDate(today)
            .map { it ?: 0 }.asLiveData()

    val weeklyCalories = nutritionRepo.getWeeklyCalories(DateUtils.nDaysAgo(6)).asLiveData()

    fun getNetCalories(eaten: Int, burned: Int) =
        NetCaloriesCalculator.calculateNetCalories(eaten, burned)

    fun getRemainingCalories(goal: Int, eaten: Int, burned: Int) =
        NetCaloriesCalculator.calculateRemainingCalories(goal, eaten, burned)

    fun getCalorieGoal(profile: UserProfile) =
        NetCaloriesCalculator.calculateCalorieGoal(profile)

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(application) as T
        }
    }
}
