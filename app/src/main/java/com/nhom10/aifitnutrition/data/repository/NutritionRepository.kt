package com.nhom10.aifitnutrition.data.repository

import com.nhom10.aifitnutrition.data.dao.FoodLogDao
import com.nhom10.aifitnutrition.data.dao.WaterLogDao
import com.nhom10.aifitnutrition.data.dao.UserProfileDao
import com.nhom10.aifitnutrition.data.model.FoodLog
import com.nhom10.aifitnutrition.data.model.WaterLog
import com.nhom10.aifitnutrition.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

class NutritionRepository(
    private val foodLogDao: FoodLogDao,
    private val waterLogDao: WaterLogDao,
    private val userProfileDao: UserProfileDao
) {
    // Food
    suspend fun insertFoodLog(foodLog: FoodLog) = foodLogDao.insert(foodLog)
    suspend fun updateFoodLog(foodLog: FoodLog) = foodLogDao.update(foodLog)
    suspend fun deleteFoodLog(foodLog: FoodLog) = foodLogDao.delete(foodLog)

    fun getFoodLogsByDate(date: String): Flow<List<FoodLog>> =
        foodLogDao.getFoodLogsByDate(date)

    fun getTotalCaloriesForDate(date: String): Flow<Int?> =
        foodLogDao.getTotalCaloriesForDate(date)

    fun getTotalProteinForDate(date: String): Flow<Float?> =
        foodLogDao.getTotalProteinForDate(date)

    fun getTotalCarbsForDate(date: String): Flow<Float?> =
        foodLogDao.getTotalCarbsForDate(date)

    fun getTotalFatForDate(date: String): Flow<Float?> =
        foodLogDao.getTotalFatForDate(date)

    fun getWeeklyCalories(startDate: String) =
        foodLogDao.getWeeklyCalories(startDate)

    fun getFoodLogsByMeal(mealType: String, date: String) =
        foodLogDao.getFoodLogsByMeal(mealType, date)

    // Water
    suspend fun insertWaterLog(waterLog: WaterLog) = waterLogDao.insert(waterLog)
    suspend fun deleteWaterLog(waterLog: WaterLog) = waterLogDao.delete(waterLog)
    fun getTotalWaterForDate(date: String): Flow<Int?> = waterLogDao.getTotalWaterForDate(date)
    fun getWaterLogsByDate(date: String) = waterLogDao.getWaterLogsByDate(date)

    // User profile
    fun getUserProfile(): Flow<UserProfile?> = userProfileDao.getUserProfile()
    suspend fun getUserProfileOnce(): UserProfile? = userProfileDao.getUserProfileOnce()
    suspend fun saveUserProfile(profile: UserProfile) = userProfileDao.insert(profile)
    suspend fun updateUserProfile(profile: UserProfile) = userProfileDao.update(profile)
}
