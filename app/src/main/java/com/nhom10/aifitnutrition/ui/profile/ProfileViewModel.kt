package com.nhom10.aifitnutrition.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.data.model.UserProfile
import com.nhom10.aifitnutrition.data.model.WeightLog
import com.nhom10.aifitnutrition.data.repository.FitnessRepository
import com.nhom10.aifitnutrition.data.repository.NutritionRepository
import com.nhom10.aifitnutrition.util.DateUtils
import com.nhom10.aifitnutrition.util.NetCaloriesCalculator
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val nutritionRepo = NutritionRepository(db.foodLogDao(), db.waterLogDao(), db.userProfileDao())
    private val fitnessRepo = FitnessRepository(db.workoutLogDao(), db.weightLogDao())

    val userProfile = nutritionRepo.getUserProfile().asLiveData()
    val weightHistory = fitnessRepo.getAllWeightLogs().asLiveData()
    val recentWeightHistory = fitnessRepo.getWeightLogsFromDate(DateUtils.nDaysAgo(30)).asLiveData()

    private val _snackbarMessage = MutableLiveData<String?>()
    val snackbarMessage: LiveData<String?> = _snackbarMessage

    fun saveProfile(profile: UserProfile) {
        viewModelScope.launch {
            val updatedProfile = profile.copy(
                dailyCalorieGoal = NetCaloriesCalculator.calculateCalorieGoal(profile)
            )
            nutritionRepo.saveUserProfile(updatedProfile)
            _snackbarMessage.value = "Profile saved! ✅"
        }
    }

    fun updateProfile(profile: UserProfile) {
        viewModelScope.launch {
            val updatedProfile = profile.copy(
                dailyCalorieGoal = NetCaloriesCalculator.calculateCalorieGoal(profile)
            )
            nutritionRepo.updateUserProfile(updatedProfile)
            _snackbarMessage.value = "Profile updated! ✅"
        }
    }

    fun logWeight(weightKg: Float) {
        viewModelScope.launch {
            fitnessRepo.insertWeightLog(
                WeightLog(weightKg = weightKg, date = DateUtils.today())
            )
            _snackbarMessage.value = "Weight logged: ${weightKg}kg"
        }
    }

    fun clearSnackbar() { _snackbarMessage.value = null }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(application) as T
        }
    }
}
