package com.nhom10.aifitnutrition.ui.workout

import android.content.Context

object ExerciseLibraryLoader {

    fun loadFromAssets(context: Context): List<ExerciseLibraryItem> {
        return try {
            // First try to load from assets
            context.assets.open("exercise_library.csv").bufferedReader().use { reader ->
                val lines = reader.readLines()
                if (lines.size <= 1) return emptyList()

                lines.drop(1)
                    .mapNotNull { parseCsvLine(it) }
                    .map { columns ->
                        if (columns.size < 19) return@map null
                        ExerciseLibraryItem(
                            exerciseId = columns.getOrNull(0) ?: "",
                            nameVi = columns.getOrNull(1) ?: "",
                            nameEn = columns.getOrNull(2) ?: "",
                            category = columns.getOrNull(3) ?: "",
                            difficulty = columns.getOrNull(4) ?: "",
                            defaultDurationSec = columns.getOrNull(5)?.toIntOrNull(),
                            defaultReps = columns.getOrNull(6)?.toIntOrNull(),
                            restSec = columns.getOrNull(7)?.toIntOrNull() ?: 20,
                            equipment = columns.getOrNull(8) ?: "",
                            primaryMuscle = columns.getOrNull(9) ?: "",
                            gifUrl = columns.getOrNull(17) ?: "",
                            thumbnailUrl = columns.getOrNull(18) ?: ""
                        )
                    }
                    .filterNotNull()
            }
        } catch (_: Exception) {
            // If assets loading fails, try loading from the project directory
            try {
                val csvFile = java.io.File("../../exercise_library.csv/exercise_library.csv")
                if (!csvFile.exists()) return emptyList()
                
                csvFile.bufferedReader().use { reader ->
                    val lines = reader.readLines()
                    if (lines.size <= 1) return emptyList()

                    lines.drop(1)
                        .mapNotNull { parseCsvLine(it) }
                        .map { columns ->
                            if (columns.size < 19) return@map null
                            ExerciseLibraryItem(
                                exerciseId = columns.getOrNull(0) ?: "",
                                nameVi = columns.getOrNull(1) ?: "",
                                nameEn = columns.getOrNull(2) ?: "",
                                category = columns.getOrNull(3) ?: "",
                                difficulty = columns.getOrNull(4) ?: "",
                                defaultDurationSec = columns.getOrNull(5)?.toIntOrNull(),
                                defaultReps = columns.getOrNull(6)?.toIntOrNull(),
                                restSec = columns.getOrNull(7)?.toIntOrNull() ?: 20,
                                equipment = columns.getOrNull(8) ?: "",
                                primaryMuscle = columns.getOrNull(9) ?: "",
                                gifUrl = columns.getOrNull(17) ?: "",
                                thumbnailUrl = columns.getOrNull(18) ?: ""
                            )
                        }
                        .filterNotNull()
                }
            } catch (_: Exception) {
                emptyList()
            }
        }
    }

    private fun parseCsvLine(line: String): List<String>? {
        if (line.isBlank()) return null
        val result = mutableListOf<String>()
        val current = StringBuilder()
        var inQuotes = false

        line.forEach { ch ->
            when {
                ch == '"' -> inQuotes = !inQuotes
                ch == ',' && !inQuotes -> {
                    result.add(current.toString())
                    current.clear()
                }
                else -> current.append(ch)
            }
        }
        result.add(current.toString())
        return result
    }
}
