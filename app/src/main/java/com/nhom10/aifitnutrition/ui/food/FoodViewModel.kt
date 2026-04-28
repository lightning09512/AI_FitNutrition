package com.nhom10.aifitnutrition.ui.food

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.*
import com.nhom10.aifitnutrition.BuildConfig
import com.nhom10.aifitnutrition.ai.FoodAnalysisResult
import com.nhom10.aifitnutrition.ai.GeminiService
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.data.model.FoodLog
import com.nhom10.aifitnutrition.data.repository.NutritionRepository
import com.nhom10.aifitnutrition.util.DateUtils
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val repo = NutritionRepository(db.foodLogDao(), db.waterLogDao(), db.userProfileDao())
    private val geminiService = GeminiService(BuildConfig.GEMINI_API_KEY)

    val today = DateUtils.today()

    val breakfastLogs = repo.getFoodLogsByMeal("breakfast", today).asLiveData()
    val lunchLogs = repo.getFoodLogsByMeal("lunch", today).asLiveData()
    val dinnerLogs = repo.getFoodLogsByMeal("dinner", today).asLiveData()
    val snackLogs = repo.getFoodLogsByMeal("snack", today).asLiveData()

    private val _analysisState = MutableLiveData<FoodAnalysisState>(FoodAnalysisState.Idle)
    val analysisState: LiveData<FoodAnalysisState> = _analysisState

    private val _snackbarMessage = MutableLiveData<String?>()
    val snackbarMessage: LiveData<String?> = _snackbarMessage

    fun analyzeFoodImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _analysisState.value = FoodAnalysisState.Loading
            val result = geminiService.analyzeFoodImage(bitmap)
            if (result.isSuccess) {
                _analysisState.value = FoodAnalysisState.Success(result.getOrThrow())
            } else {
                _analysisState.value = FoodAnalysisState.Error(
                    result.exceptionOrNull()?.message ?: "Analysis failed"
                )
            }
        }
    }

    fun insertFoodLog(foodLog: FoodLog) {
        viewModelScope.launch {
            repo.insertFoodLog(foodLog)
            _snackbarMessage.value = "Food logged successfully! 🎉"
        }
    }

    fun deleteFoodLog(foodLog: FoodLog) {
        viewModelScope.launch { repo.deleteFoodLog(foodLog) }
    }

    fun clearAnalysisState() {
        _analysisState.value = FoodAnalysisState.Idle
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(application) as T
        }
    }
}

sealed class FoodAnalysisState {
    object Idle : FoodAnalysisState()
    object Loading : FoodAnalysisState()
    data class Success(val result: FoodAnalysisResult) : FoodAnalysisState()
    data class Error(val message: String) : FoodAnalysisState()
}
