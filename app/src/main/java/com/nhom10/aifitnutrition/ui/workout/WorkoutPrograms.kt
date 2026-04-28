package com.nhom10.aifitnutrition.ui.workout

data class WorkoutExercise(
    val name: String,
    val target: String,
    val restSeconds: Int,
    val durationSeconds: Int = 30,
    val mediaPath: String = ""
)

data class WorkoutProgram(
    val id: String,
    val title: String,
    val subtitle: String,
    val workoutType: String,
    val intensity: String,
    val durationMinutes: Int,
    val estimatedKcal: Int,
    val exercises: List<WorkoutExercise>
)

data class ExerciseLibraryItem(
    val exerciseId: String,
    val nameVi: String,
    val nameEn: String,
    val category: String,
    val difficulty: String,
    val defaultDurationSec: Int?,
    val defaultReps: Int?,
    val restSec: Int,
    val equipment: String,
    val primaryMuscle: String,
    val gifUrl: String,
    val thumbnailUrl: String
)

object WorkoutPrograms {
    private var dynamicPlans: List<WorkoutProgram>? = null

    private val defaultPlans = listOf(
        WorkoutProgram(
            id = "full_body_beginner",
            title = "Full Body Beginner",
            subtitle = "Build base strength",
            workoutType = "Gym",
            intensity = "Moderate",
            durationMinutes = 30,
            estimatedKcal = 220,
            exercises = listOf(
                WorkoutExercise("Bodyweight Squat", "3 x 15 reps", 30, 35),
                WorkoutExercise("Knee Push-up", "3 x 10 reps", 30, 35),
                WorkoutExercise("Glute Bridge", "3 x 15 reps", 30, 35),
                WorkoutExercise("Plank", "3 x 30 sec", 40, 30),
                WorkoutExercise("Jumping Jacks", "3 x 30 sec", 30, 30)
            )
        ),
        WorkoutProgram(
            id = "abs_core",
            title = "Abs & Core",
            subtitle = "Tighten mid-section",
            workoutType = "HIIT",
            intensity = "Intense",
            durationMinutes = 20,
            estimatedKcal = 200,
            exercises = listOf(
                WorkoutExercise("Crunch", "4 x 20 reps", 20, 35),
                WorkoutExercise("Bicycle Crunch", "3 x 20 reps", 20, 35),
                WorkoutExercise("Leg Raise", "3 x 15 reps", 25, 35),
                WorkoutExercise("Mountain Climber", "4 x 30 sec", 20, 30),
                WorkoutExercise("Side Plank", "3 x 25 sec/side", 25, 30)
            )
        ),
        WorkoutProgram(
            id = "chest_arms",
            title = "Chest & Arms",
            subtitle = "Upper-body focus",
            workoutType = "Gym",
            intensity = "Moderate",
            durationMinutes = 30,
            estimatedKcal = 250,
            exercises = listOf(
                WorkoutExercise("Push-up", "4 x 12 reps", 35, 35),
                WorkoutExercise("Chair Dip", "3 x 12 reps", 35, 35),
                WorkoutExercise("Pike Push-up", "3 x 10 reps", 35, 35),
                WorkoutExercise("Tricep Extension (Band)", "3 x 15 reps", 30, 35),
                WorkoutExercise("Diamond Push-up", "2 x 8 reps", 45, 35)
            )
        ),
        WorkoutProgram(
            id = "legs_glutes",
            title = "Butt & Legs",
            subtitle = "Lower-body burn",
            workoutType = "Gym",
            intensity = "Intense",
            durationMinutes = 25,
            estimatedKcal = 240,
            exercises = listOf(
                WorkoutExercise("Squat", "4 x 15 reps", 30, 35),
                WorkoutExercise("Reverse Lunge", "3 x 12 reps/leg", 35, 35),
                WorkoutExercise("Bulgarian Split Squat", "3 x 10 reps/leg", 40, 35),
                WorkoutExercise("Glute Kickback", "3 x 15 reps/leg", 25, 35),
                WorkoutExercise("Wall Sit", "3 x 40 sec", 30, 40)
            )
        ),
        WorkoutProgram(
            id = "lose_fat",
            title = "Lose Fat HIIT",
            subtitle = "Fast calorie burn",
            workoutType = "HIIT",
            intensity = "Intense",
            durationMinutes = 20,
            estimatedKcal = 230,
            exercises = listOf(
                WorkoutExercise("High Knees", "5 x 30 sec", 20, 30),
                WorkoutExercise("Burpees", "4 x 12 reps", 30, 35),
                WorkoutExercise("Jump Squat", "4 x 15 reps", 30, 35),
                WorkoutExercise("Mountain Climber", "4 x 30 sec", 20, 30),
                WorkoutExercise("Plank Jack", "3 x 30 sec", 20, 30)
            )
        )
    )

    fun setDynamicPlans(plans: List<WorkoutProgram>) {
        dynamicPlans = plans
    }

    fun getPlans(): List<WorkoutProgram> = dynamicPlans ?: defaultPlans

    fun findProgramById(id: String): WorkoutProgram? = getPlans().firstOrNull { it.id == id }

    fun fromExerciseLibrary(items: List<ExerciseLibraryItem>): List<WorkoutProgram> {
        if (items.isEmpty()) return defaultPlans

        val grouped = items.groupBy { it.category.lowercase() }
        return listOfNotNull(
            buildProgram("full_body", "Full Body", "Build base strength", "Gym", "Moderate", grouped),
            buildProgram("core", "Abs & Core", "Core stability and abs", "HIIT", "Intense", grouped),
            buildProgram("chest_arms", "Chest & Arms", "Upper-body focus", "Gym", "Moderate", grouped),
            buildProgram("legs_glutes", "Butt & Legs", "Lower-body burn", "Gym", "Intense", grouped)
        ).ifEmpty { defaultPlans }
    }

    private fun buildProgram(
        categoryKey: String,
        title: String,
        subtitle: String,
        workoutType: String,
        intensity: String,
        grouped: Map<String, List<ExerciseLibraryItem>>
    ): WorkoutProgram? {
        val exercises = grouped[categoryKey].orEmpty().take(5)
        if (exercises.isEmpty()) return null

        val duration = exercises.sumOf { exercise ->
            exercise.defaultDurationSec?.div(60)?.coerceAtLeast(1)
                ?: if (exercise.defaultReps != null) 2 else 1
        }.coerceIn(15, 45)
        val estimatedKcal = (duration * if (intensity == "Intense") 11 else 8)

        return WorkoutProgram(
            id = categoryKey,
            title = title,
            subtitle = subtitle,
            workoutType = workoutType,
            intensity = intensity,
            durationMinutes = duration,
            estimatedKcal = estimatedKcal,
            exercises = exercises.map {
                val target = it.defaultDurationSec?.let { sec -> "${sec}s" }
                    ?: "${it.defaultReps ?: 12} reps"
                val duration = it.defaultDurationSec ?: if (it.defaultReps != null) 35 else 30
                val media = if (it.gifUrl.isNotBlank()) it.gifUrl else it.thumbnailUrl
                WorkoutExercise(name = it.nameEn, target = target, restSeconds = it.restSec)
                    .copy(durationSeconds = duration, mediaPath = media)
            }
        )
    }
}
