package com.nhom10.aifitnutrition.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nhom10.aifitnutrition.data.dao.*
import com.nhom10.aifitnutrition.data.model.*

@Database(
    entities = [
        FoodLog::class,
        WorkoutLog::class,
        WaterLog::class,
        WeightLog::class,
        ChatMessage::class,
        UserProfile::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodLogDao(): FoodLogDao
    abstract fun workoutLogDao(): WorkoutLogDao
    abstract fun waterLogDao(): WaterLogDao
    abstract fun weightLogDao(): WeightLogDao
    abstract fun chatMessageDao(): ChatMessageDao
    abstract fun userProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitnutrition_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
