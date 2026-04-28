package com.nhom10.aifitnutrition.ui.food

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.nhom10.aifitnutrition.R

public class FoodCameraFragmentDirections private constructor() {
  public companion object {
    public fun actionCameraToFood(): NavDirections =
        ActionOnlyNavDirections(R.id.action_camera_to_food)
  }
}
