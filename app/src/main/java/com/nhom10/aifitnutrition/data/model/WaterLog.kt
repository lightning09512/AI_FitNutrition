package com.nhom10.aifitnutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_logs")
data class WaterLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amountMl: Int,
    val date: String,
    val timestamp: Long = System.currentTimeMillis()
)
