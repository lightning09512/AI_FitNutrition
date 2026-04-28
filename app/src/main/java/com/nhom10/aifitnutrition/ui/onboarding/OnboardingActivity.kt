package com.nhom10.aifitnutrition.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nhom10.aifitnutrition.App
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.data.database.AppDatabase
import com.nhom10.aifitnutrition.data.model.UserProfile
import com.nhom10.aifitnutrition.databinding.ActivityOnboardingBinding
import com.nhom10.aifitnutrition.ui.MainActivity
import com.nhom10.aifitnutrition.util.NetCaloriesCalculator
import kotlinx.coroutines.launch

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        App.applyLocale(App.getSavedLanguage(this))
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            val name = binding.etName.text?.toString()?.trim() ?: ""
            val age = binding.etAge.text?.toString()?.toIntOrNull() ?: 25
            val height = binding.etHeight.text?.toString()?.toFloatOrNull() ?: 170f
            val weight = binding.etWeight.text?.toString()?.toFloatOrNull() ?: 70f

            val goalType = when (binding.chipGroupGoal.checkedChipId) {
                binding.chipLose.id -> "lose"
                binding.chipGain.id -> "gain"
                else -> "maintain"
            }

            if (name.isEmpty()) {
                Toast.makeText(this, getString(R.string.onboarding_enter_name), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val profile = UserProfile(
                name = name,
                ageYears = age,
                heightCm = height,
                weightKg = weight,
                goalType = goalType,
                targetWeightKg = weight
            )
            val withGoal = profile.copy(
                dailyCalorieGoal = NetCaloriesCalculator.calculateCalorieGoal(profile)
            )

            lifecycleScope.launch {
                AppDatabase.getDatabase(applicationContext).userProfileDao().insert(withGoal)
                runOnUiThread {
                    startActivity(Intent(this@OnboardingActivity, MainActivity::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
        }
    }
}
