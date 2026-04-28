package com.nhom10.aifitnutrition.util

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object StreakManager {
    private const val PREFS_NAME = "streak_prefs"
    private const val KEY_STREAK_COUNT = "streak_count"
    private const val KEY_LAST_ACTIVE_DATE = "last_active_date"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun getTodayDateString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun getYesterdayDateString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        return sdf.format(cal.time)
    }

    fun getStreak(context: Context): Int {
        val prefs = getPrefs(context)
        val lastActiveDate = prefs.getString(KEY_LAST_ACTIVE_DATE, "") ?: ""
        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()
        val currentStreak = prefs.getInt(KEY_STREAK_COUNT, 0)

        return when (lastActiveDate) {
            today, yesterday -> currentStreak
            else -> 0 // If they missed yesterday, streak is visually 0 until they log today
        }
    }

    /**
     * Call this when the user performs an activity (logs food, water, or workout).
     * @return true if the streak was just extended today (to trigger confetti).
     */
    fun extendStreak(context: Context): Boolean {
        val prefs = getPrefs(context)
        val lastActiveDate = prefs.getString(KEY_LAST_ACTIVE_DATE, "") ?: ""
        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()
        var currentStreak = prefs.getInt(KEY_STREAK_COUNT, 0)

        // Already extended today
        if (lastActiveDate == today) {
            return false
        }

        // Extended yesterday, so increment
        if (lastActiveDate == yesterday) {
            currentStreak += 1
        } else {
            // Missed a day (or first time), reset to 1
            currentStreak = 1
        }

        prefs.edit()
            .putInt(KEY_STREAK_COUNT, currentStreak)
            .putString(KEY_LAST_ACTIVE_DATE, today)
            .apply()

        return true
    }
}
