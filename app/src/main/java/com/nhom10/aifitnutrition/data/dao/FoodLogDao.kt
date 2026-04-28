package com.nhom10.aifitnutrition.data.dao

import androidx.room.*
import com.nhom10.aifitnutrition.data.model.FoodLog
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodLog: FoodLog): Long

    @Update
    suspend fun update(foodLog: FoodLog)

    @Delete
    suspend fun delete(foodLog: FoodLog)

    @Query("SELECT * FROM food_logs WHERE date = :date ORDER BY timestamp ASC")
    fun getFoodLogsByDate(date: String): Flow<List<FoodLog>>

    @Query("SELECT SUM(calories) FROM food_logs WHERE date = :date")
    fun getTotalCaloriesForDate(date: String): Flow<Int?>

    @Query("SELECT SUM(proteinG) FROM food_logs WHERE date = :date")
    fun getTotalProteinForDate(date: String): Flow<Float?>

    @Query("SELECT SUM(carbsG) FROM food_logs WHERE date = :date")
    fun getTotalCarbsForDate(date: String): Flow<Float?>

    @Query("SELECT SUM(fatG) FROM food_logs WHERE date = :date")
    fun getTotalFatForDate(date: String): Flow<Float?>

    @Query("SELECT date, SUM(calories) as totalCals FROM food_logs WHERE date >= :startDate GROUP BY date ORDER BY date ASC")
    fun getWeeklyCalories(startDate: String): Flow<List<DateCalories>>

    @Query("SELECT * FROM food_logs WHERE mealType = :mealType AND date = :date ORDER BY timestamp ASC")
    fun getFoodLogsByMeal(mealType: String, date: String): Flow<List<FoodLog>>

    @Query("SELECT * FROM food_logs ORDER BY timestamp DESC LIMIT 20")
    fun getRecentFoodLogs(): Flow<List<FoodLog>>
}

data class DateCalories(val date: String, val totalCals: Int)
