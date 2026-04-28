package com.nhom10.aifitnutrition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_logs")
data class WeightLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val weightKg: Float,
    val date: String,
    val timestamp: Long = System.currentTimeMillis()
)
