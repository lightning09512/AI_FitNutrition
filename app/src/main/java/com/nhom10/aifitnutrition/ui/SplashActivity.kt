package com.nhom10.aifitnutrition.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.nhom10.aifitnutrition.App
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.databinding.ActivitySplashBinding
import com.nhom10.aifitnutrition.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        App.applyLocale(App.getSavedLanguage(this))
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start logo animation
        binding.ivLogo.animate().scaleX(1.1f).scaleY(1.1f).setDuration(800)
            .withEndAction {
                binding.ivLogo.animate().scaleX(1f).scaleY(1f).setDuration(400).start()
            }.start()

        binding.tvAppName.alpha = 0f
        binding.tvAppName.animate().alpha(1f).setDuration(600).setStartDelay(300).start()
        binding.tvTagline.alpha = 0f
        binding.tvTagline.animate().alpha(1f).setDuration(600).setStartDelay(600).start()

        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val profile = db.userProfileDao().getUserProfileOnce()

            delay(2000L)

            withContext(Dispatchers.Main) {
                val intent = if (profile == null) {
                    Intent(this@SplashActivity, OnboardingActivity::class.java)
                } else {
                    Intent(this@SplashActivity, MainActivity::class.java)
                }
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}
