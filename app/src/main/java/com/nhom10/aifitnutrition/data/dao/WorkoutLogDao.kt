package com.nhom10.aifitnutrition.data.dao

import androidx.room.*
import com.nhom10.aifitnutrition.data.model.WorkoutLog
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutLog: WorkoutLog): Long

    @Delete
    suspend fun delete(workoutLog: WorkoutLog)

    @Query("SELECT * FROM workout_logs WHERE date = :date ORDER BY timestamp DESC")
    fun getWorkoutsByDate(date: String): Flow<List<WorkoutLog>>

    @Query("SELECT SUM(caloriesBurned) FROM workout_logs WHERE date = :date")
    fun getTotalCaloriesBurnedForDate(date: String): Flow<Int?>

    @Query("SELECT * FROM workout_logs ORDER BY timestamp DESC LIMIT 30")
    fun getRecentWorkouts(): Flow<List<WorkoutLog>>
}
