package com.nhom10.aifitnutrition.data.dao

import androidx.room.*
import com.nhom10.aifitnutrition.data.model.WaterLog
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(waterLog: WaterLog): Long

    @Delete
    suspend fun delete(waterLog: WaterLog)

    @Query("SELECT * FROM water_logs WHERE date = :date ORDER BY timestamp DESC")
    fun getWaterLogsByDate(date: String): Flow<List<WaterLog>>

    @Query("SELECT SUM(amountMl) FROM water_logs WHERE date = :date")
    fun getTotalWaterForDate(date: String): Flow<Int?>
}
