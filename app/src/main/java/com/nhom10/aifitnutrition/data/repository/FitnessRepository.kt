package com.nhom10.aifitnutrition.data.repository

import com.nhom10.aifitnutrition.data.dao.WorkoutLogDao
import com.nhom10.aifitnutrition.data.dao.WeightLogDao
import com.nhom10.aifitnutrition.data.model.WorkoutLog
import com.nhom10.aifitnutrition.data.model.WeightLog
import kotlinx.coroutines.flow.Flow

class FitnessRepository(
    private val workoutLogDao: WorkoutLogDao,
    private val weightLogDao: WeightLogDao
) {
    // Workout
    suspend fun insertWorkout(workoutLog: WorkoutLog) = workoutLogDao.insert(workoutLog)
    suspend fun deleteWorkout(workoutLog: WorkoutLog) = workoutLogDao.delete(workoutLog)
    fun getWorkoutsByDate(date: String): Flow<List<WorkoutLog>> = workoutLogDao.getWorkoutsByDate(date)
    fun getTotalCaloriesBurnedForDate(date: String): Flow<Int?> = workoutLogDao.getTotalCaloriesBurnedForDate(date)
    fun getRecentWorkouts(): Flow<List<WorkoutLog>> = workoutLogDao.getRecentWorkouts()

    // Weight
    suspend fun insertWeightLog(weightLog: WeightLog) = weightLogDao.insert(weightLog)
    suspend fun deleteWeightLog(weightLog: WeightLog) = weightLogDao.delete(weightLog)
    fun getAllWeightLogs(): Flow<List<WeightLog>> = weightLogDao.getAllWeightLogs()
    suspend fun getLatestWeight(): WeightLog? = weightLogDao.getLatestWeight()
    fun getWeightLogsFromDate(startDate: String): Flow<List<WeightLog>> = weightLogDao.getWeightLogsFromDate(startDate)
}
