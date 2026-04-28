package com.nhom10.aifitnutrition.util

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import com.nhom10.aifitnutrition.data.model.UserProfile
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object PdfExporter {

    fun exportHealthReport(context: Context, profile: UserProfile): File? {
        return try {
            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas

            val titlePaint = Paint().apply {
                color = Color.parseColor("#00D4AA")
                textSize = 28f
                isFakeBoldText = true
            }
            val headerPaint = Paint().apply {
                color = Color.parseColor("#FFFFFF")
                textSize = 18f
                isFakeBoldText = true
            }
            val bodyPaint = Paint().apply {
                color = Color.parseColor("#B0B8CC")
                textSize = 13f
            }
            val bgPaint = Paint().apply {
                color = Color.parseColor("#0D0F14")
                style = Paint.Style.FILL
            }
            val cardPaint = Paint().apply {
                color = Color.parseColor("#1E2130")
                style = Paint.Style.FILL
            }

            // Background
            canvas.drawRect(0f, 0f, 595f, 842f, bgPaint)

            // Header accent bar
            val accentPaint = Paint().apply {
                color = Color.parseColor("#00D4AA")
                style = Paint.Style.FILL
            }
            canvas.drawRect(0f, 0f, 595f, 90f, accentPaint)

            // Title
            val titleWhite = Paint().apply {
                color = Color.WHITE
                textSize = 26f
                isFakeBoldText = true
            }
            canvas.drawText("AI FitNutrition", 32f, 45f, titleWhite)
            val subtitlePaint = Paint().apply {
                color = Color.parseColor("#0D0F14")
                textSize = 13f
            }
            canvas.drawText("Health Report", 32f, 68f, subtitlePaint)

            val dateStr = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()).format(Date())
            val datePaint = Paint().apply {
                color = Color.parseColor("#0D0F14")
                textSize = 11f
            }
            canvas.drawText(dateStr, 400f, 55f, datePaint)

            // Profile card
            canvas.drawRoundRect(android.graphics.RectF(24f, 108f, 571f, 280f), 16f, 16f, cardPaint)
            canvas.drawText("👤 Profile", 40f, 140f, headerPaint)

            val rowLeft = bodyPaint.apply { textSize = 13f }
            canvas.drawText("Name:", 40f, 168f, bodyPaint)
            canvas.drawText(profile.name, 160f, 168f, Paint().apply { color = Color.WHITE; textSize = 13f })
            canvas.drawText("Age:", 40f, 192f, bodyPaint)
            canvas.drawText("${profile.ageYears} years", 160f, 192f, Paint().apply { color = Color.WHITE; textSize = 13f })
            canvas.drawText("Height:", 40f, 216f, bodyPaint)
            canvas.drawText("${profile.heightCm} cm", 160f, 216f, Paint().apply { color = Color.WHITE; textSize = 13f })
            canvas.drawText("Weight:", 40f, 240f, bodyPaint)
            canvas.drawText("${profile.weightKg} kg", 160f, 240f, Paint().apply { color = Color.WHITE; textSize = 13f })
            canvas.drawText("Goal:", 40f, 264f, bodyPaint)
            canvas.drawText(profile.goalType.replaceFirstChar { it.uppercase() }, 160f, 264f, Paint().apply { color = Color.parseColor("#00D4AA"); textSize = 13f; isFakeBoldText = true })

            // Goal card
            canvas.drawRoundRect(android.graphics.RectF(24f, 296f, 571f, 430f), 16f, 16f, cardPaint)
            canvas.drawText("🎯 Daily Targets", 40f, 328f, headerPaint)
            canvas.drawText("Calories Goal:", 40f, 358f, bodyPaint)
            canvas.drawText("${profile.dailyCalorieGoal} kcal", 220f, 358f, Paint().apply { color = Color.parseColor("#00D4AA"); textSize = 14f; isFakeBoldText = true })
            canvas.drawText("Protein:", 40f, 382f, bodyPaint)
            canvas.drawText("${profile.proteinGoalG.toInt()}g", 220f, 382f, Paint().apply { color = Color.parseColor("#4C9EFF"); textSize = 13f })
            canvas.drawText("Carbs:", 40f, 404f, bodyPaint)
            canvas.drawText("${profile.carbsGoalG.toInt()}g", 220f, 404f, Paint().apply { color = Color.parseColor("#FFD166"); textSize = 13f })
            canvas.drawText("Fat:", 40f, 424f, bodyPaint)
            canvas.drawText("${profile.fatGoalG.toInt()}g", 220f, 424f, Paint().apply { color = Color.parseColor("#FF6B35"); textSize = 13f })

            // Footer
            val footerPaint = Paint().apply {
                color = Color.parseColor("#6B7591")
                textSize = 11f
            }
            canvas.drawText("Generated by AI FitNutrition App", 32f, 820f, footerPaint)

            document.finishPage(page)

            // Save to public Downloads folder so user can find it easily
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            if (!dir.exists()) dir.mkdirs()
            
            val file = File(dir, "FitNutrition_Report_${System.currentTimeMillis()}.pdf")
            document.writeTo(FileOutputStream(file))
            document.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
