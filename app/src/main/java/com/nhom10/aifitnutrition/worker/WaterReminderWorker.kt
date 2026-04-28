package com.nhom10.aifitnutrition.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.nhom10.aifitnutrition.util.NotificationHelper
import java.util.Calendar

class WaterReminderWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        // Do not remind during the night (e.g., between 23:00 and 06:00)
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if (hour in 7..22) {
            NotificationHelper.createNotificationChannel(applicationContext)
            NotificationHelper.showNotification(
                applicationContext,
                "Time to Hydrate! 💧",
                "Drink a glass of water to reach your daily goal.",
                1001
            )
        }
        return Result.success()
    }
}
