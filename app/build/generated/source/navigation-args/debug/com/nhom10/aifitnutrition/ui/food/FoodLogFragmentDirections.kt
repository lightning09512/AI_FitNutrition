package com.nhom10.aifitnutrition.ui.food

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.nhom10.aifitnutrition.R

public class FoodLogFragmentDirections private constructor() {
  public companion object {
    public fun actionFoodToCamera(): NavDirections =
        ActionOnlyNavDirections(R.id.action_food_to_camera)
  }
}
