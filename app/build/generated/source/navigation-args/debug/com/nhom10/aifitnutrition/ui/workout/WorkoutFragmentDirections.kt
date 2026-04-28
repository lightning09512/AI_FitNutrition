package com.nhom10.aifitnutrition.ui.workout

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.nhom10.aifitnutrition.R

public class WorkoutFragmentDirections private constructor() {
  public companion object {
    public fun actionWorkoutToPlayer(): NavDirections =
        ActionOnlyNavDirections(R.id.action_workout_to_player)
  }
}
