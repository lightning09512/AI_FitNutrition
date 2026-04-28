package com.nhom10.aifitnutrition

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

class App : Application() {

    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val KEY_LANGUAGE = "selected_language"

        /** Đọc language code đã lưu (không cần context nặng) */
        fun getSavedLanguage(context: Context): String {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getString(KEY_LANGUAGE, "en") ?: "en"
        }

        /** Lưu language code */
        fun saveLanguage(context: Context, langCode: String) {
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_LANGUAGE, langCode)
                .apply()
        }

        /**
         * Apply locale - gọi ở onCreate của Application và Activity.attachBaseContext
         * API < 33: dùng AppCompatDelegate
         */
        fun applyLocale(langCode: String) {
            val localeList = LocaleListCompat.forLanguageTags(langCode)
            AppCompatDelegate.setApplicationLocales(localeList)
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Apply saved locale ngay khi app khởi động
        val lang = getSavedLanguage(this)
        applyLocale(lang)
    }
}
