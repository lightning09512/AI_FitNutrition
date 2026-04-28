package com.nhom10.aifitnutrition.ui.workout

import android.app.Application
import androidx.lifecycle.*
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.data.model.WorkoutLog
import com.nhom10.aifitnutrition.data.repository.FitnessRepository
import com.nhom10.aifitnutrition.data.repository.NutritionRepository
import com.nhom10.aifitnutrition.util.DateUtils
import com.nhom10.aifitnutrition.util.NetCaloriesCalculator
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val repo = FitnessRepository(db.workoutLogDao(), db.weightLogDao())
    private val nutritionRepo = NutritionRepository(db.foodLogDao(), db.waterLogDao(), db.userProfileDao())

    val today = DateUtils.today()
    val workoutsToday = repo.getWorkoutsByDate(today).asLiveData()
    val totalCaloriesBurnedToday = repo.getTotalCaloriesBurnedForDate(today).asLiveData()
    val recentWorkouts = repo.getRecentWorkouts().asLiveData()
    val userProfile = nutritionRepo.getUserProfile().asLiveData()

    private val _snackbarMessage = MutableLiveData<String?>()
    val snackbarMessage: LiveData<String?> = _snackbarMessage

    fun insertWorkout(workoutLog: WorkoutLog) {
        viewModelScope.launch {
            repo.insertWorkout(workoutLog)
            _snackbarMessage.value = "Workout logged! 💪 ${workoutLog.caloriesBurned} kcal burned"
        }
    }

    fun deleteWorkout(workoutLog: WorkoutLog) {
        viewModelScope.launch { repo.deleteWorkout(workoutLog) }
    }

    fun estimateCalories(type: String, duration: Int, intensity: String, weightKg: Float): Int =
        NetCaloriesCalculator.estimateCaloriesBurned(type, duration, weightKg, intensity)

    fun clearSnackbar() { _snackbarMessage.value = null }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewModel(application) as T
        }
    }
}
