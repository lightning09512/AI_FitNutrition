package com.nhom10.aifitnutrition.worker

import android.content.Context
import androidx.work.*
import com.nhom10.aifitnutrition.util.NotificationHelper
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MealReminderWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val mealType = inputData.getString("MEAL_TYPE") ?: "Meal"
        
        NotificationHelper.createNotificationChannel(applicationContext)
        NotificationHelper.showNotification(
            applicationContext,
            "Time for $mealType! 🍽️",
            "Don't forget to log your $mealType to keep your streak going.",
            1002
        )

        // Schedule the next one for tomorrow (or next meal)
        scheduleNextMealReminders(applicationContext)

        return Result.success()
    }

    companion object {
        fun scheduleNextMealReminders(context: Context) {
            val workManager = WorkManager.getInstance(context)
            workManager.cancelAllWorkByTag("meal_reminders")

            scheduleMeal(context, 8, 0, "Breakfast")
            scheduleMeal(context, 12, 0, "Lunch")
            scheduleMeal(context, 19, 0, "Dinner")
        }

        private fun scheduleMeal(context: Context, hour: Int, minute: Int, mealType: String) {
            val currentDate = Calendar.getInstance()
            val dueDate = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
            }

            if (dueDate.before(currentDate)) {
                dueDate.add(Calendar.HOUR_OF_DAY, 24)
            }

            val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

            val data = workDataOf("MEAL_TYPE" to mealType)
            val request = OneTimeWorkRequestBuilder<MealReminderWorker>()
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .addTag("meal_reminders")
                .setInputData(data)
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }
}
