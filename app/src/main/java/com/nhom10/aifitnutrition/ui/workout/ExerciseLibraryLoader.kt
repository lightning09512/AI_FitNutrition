package com.nhom10.aifitnutrition.ui.workout

import android.content.Context

object ExerciseLibraryLoader {

    fun loadFromAssets(context: Context): List<ExerciseLibraryItem> {
        return try {
            context.assets.open("exercise_library.csv").bufferedReader().use { reader ->
                val lines = reader.readLines()
                if (lines.size <= 1) return emptyList()

                lines.drop(1)
                    .mapNotNull { parseCsvLine(it) }
                    .map { columns ->
                        if (columns.size < 19) return@map null
                        ExerciseLibraryItem(
                            exerciseId = columns[0],
                            nameVi = columns[1],
                            nameEn = columns[2],
                            category = columns[3],
                            difficulty = columns[4],
                            defaultDurationSec = columns[5].toIntOrNull(),
                            defaultReps = columns[6].toIntOrNull(),
                            restSec = columns[7].toIntOrNull() ?: 20,
                            equipment = columns[8],
                            primaryMuscle = columns[9],
                            gifUrl = columns[17],
                            thumbnailUrl = columns[18]
                        )
                    }
                    .filterNotNull()
            }
        } catch (_: Exception) {
            emptyList()
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
