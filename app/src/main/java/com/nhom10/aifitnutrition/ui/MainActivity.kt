package com.nhom10.aifitnutrition.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nhom10.aifitnutrition.App
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.nhom10.aifitnutrition.worker.MealReminderWorker
import com.nhom10.aifitnutrition.worker.WaterReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    // Request permission launcher for Android 13+ Notifications
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            setupReminders()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.applyLocale(App.getSavedLanguage(this))
        super.onCreate(savedInstanceState)

        // Let the app draw behind system bars (edge-to-edge)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Tùy chỉnh xử lý sự kiện click trên BottomNavigation để fix triệt để lỗi không về được trang chủ
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId != navController.currentDestination?.id) {
                val builder = androidx.navigation.NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setRestoreState(false) // Tắt restore state để tránh lỗi kẹt tab
                
                // Nếu là tab khác, pop up về Home. Nếu là Home, clear hết các tab khác.
                builder.setPopUpTo(
                    navController.graph.startDestinationId,
                    inclusive = false,
                    saveState = false // Tắt save state để fix lỗi "không thể chuyển trở lại"
                )
                
                navController.navigate(item.itemId, null, builder.build())
            }
            true
        }
        
        // Đồng bộ UI của BottomNavigation khi lướt (tự động chọn đúng tab)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.menu.findItem(destination.id)?.isChecked = true
        }

        // Apply window insets: top padding → nav host, bottom padding → bottom nav
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Push fragment content below status bar
            binding.navHostFragment.setPadding(0, systemBars.top, 0, 0)
            // Push bottom nav above navigation bar
            binding.bottomNavigation.setPadding(0, 0, 0, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        // Hide bottom nav on camera/fullscreen fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.foodCameraFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.workoutPlayerFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
            }
        }

        checkNotificationPermissionAndSetup()
    }

    private fun checkNotificationPermissionAndSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                setupReminders()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            setupReminders()
        }
    }

    private fun setupReminders() {
        val workManager = WorkManager.getInstance(this)

        // Water reminder every 2 hours
        val waterRequest = PeriodicWorkRequestBuilder<WaterReminderWorker>(2, TimeUnit.HOURS)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "water_reminder",
            ExistingPeriodicWorkPolicy.KEEP,
            waterRequest
        )

        // Meal reminders
        MealReminderWorker.scheduleNextMealReminders(this)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()
}
