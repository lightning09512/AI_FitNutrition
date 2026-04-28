package com.nhom10.aifitnutrition.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val displayFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
    private val shortFormat = SimpleDateFormat("MMM d", Locale.getDefault())

    fun today(): String = dateFormat.format(Date())

    fun nDaysAgo(n: Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -n)
        return dateFormat.format(cal.time)
    }

    fun formatDisplay(dateStr: String): String =
        try { displayFormat.format(dateFormat.parse(dateStr)!!) } catch (e: Exception) { dateStr }

    fun formatShort(dateStr: String): String =
        try { shortFormat.format(dateFormat.parse(dateStr)!!) } catch (e: Exception) { dateStr }

    fun getGreeting(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 5..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good evening"
        }
    }

    fun getLast7Days(): List<String> = (6 downTo 0).map { nDaysAgo(it) }
}
