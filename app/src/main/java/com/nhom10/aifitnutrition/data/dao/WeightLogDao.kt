package com.nhom10.aifitnutrition.data.dao

import androidx.room.*
import com.nhom10.aifitnutrition.data.model.WeightLog
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weightLog: WeightLog): Long

    @Delete
    suspend fun delete(weightLog: WeightLog)

    @Query("SELECT * FROM weight_logs ORDER BY timestamp DESC")
    fun getAllWeightLogs(): Flow<List<WeightLog>>

    @Query("SELECT * FROM weight_logs ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestWeight(): WeightLog?

    @Query("SELECT * FROM weight_logs WHERE date >= :startDate ORDER BY timestamp ASC")
    fun getWeightLogsFromDate(startDate: String): Flow<List<WeightLog>>
}
