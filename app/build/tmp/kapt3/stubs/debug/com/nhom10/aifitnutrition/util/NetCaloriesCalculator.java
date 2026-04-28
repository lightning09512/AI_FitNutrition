package com.nhom10.aifitnutrition.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bJ\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J&\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0011\u00a8\u0006\u0015"}, d2 = {"Lcom/nhom10/aifitnutrition/util/NetCaloriesCalculator;", "", "()V", "calculateBMR", "", "profile", "Lcom/nhom10/aifitnutrition/data/model/UserProfile;", "calculateCalorieGoal", "", "calculateNetCalories", "eaten", "burned", "calculateRemainingCalories", "goal", "calculateTDEE", "estimateCaloriesBurned", "workoutType", "", "durationMinutes", "weightKg", "intensity", "app_debug"})
public final class NetCaloriesCalculator {
    @org.jetbrains.annotations.NotNull()
    public static final com.nhom10.aifitnutrition.util.NetCaloriesCalculator INSTANCE = null;
    
    private NetCaloriesCalculator() {
        super();
    }
    
    /**
     * Calculate BMR using Mifflin-St Jeor equation
     */
    public final float calculateBMR(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.UserProfile profile) {
        return 0.0F;
    }
    
    /**
     * Calculate TDEE (Total Daily Energy Expenditure)
     */
    public final float calculateTDEE(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.UserProfile profile) {
        return 0.0F;
    }
    
    /**
     * Calculate recommended daily calorie goal based on user's goal
     */
    public final int calculateCalorieGoal(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.UserProfile profile) {
        return 0;
    }
    
    /**
     * Net calories = calories eaten - calories burned through exercise
     */
    public final int calculateNetCalories(int eaten, int burned) {
        return 0;
    }
    
    /**
     * Remaining calories = goal - net calories
     */
    public final int calculateRemainingCalories(int goal, int eaten, int burned) {
        return 0;
    }
    
    /**
     * Calculate estimated calories burned for workout
     */
    public final int estimateCaloriesBurned(@org.jetbrains.annotations.NotNull()
    java.lang.String workoutType, int durationMinutes, float weightKg, @org.jetbrains.annotations.NotNull()
    java.lang.String intensity) {
        return 0;
    }
}