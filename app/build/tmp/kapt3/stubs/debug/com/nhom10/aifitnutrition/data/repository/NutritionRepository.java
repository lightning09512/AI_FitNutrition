package com.nhom10.aifitnutrition.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\"\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u0013J\u0010\u0010\"\u001a\u0004\u0018\u00010!H\u0086@\u00a2\u0006\u0002\u0010#J\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u001a\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\u00140\u00132\u0006\u0010\'\u001a\u00020\u0016J\u0016\u0010(\u001a\u00020)2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010*\u001a\u00020)2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010-J\u0016\u0010.\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010/\u001a\u00020\n2\u0006\u0010,\u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010-R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/nhom10/aifitnutrition/data/repository/NutritionRepository;", "", "foodLogDao", "Lcom/nhom10/aifitnutrition/data/dao/FoodLogDao;", "waterLogDao", "Lcom/nhom10/aifitnutrition/data/dao/WaterLogDao;", "userProfileDao", "Lcom/nhom10/aifitnutrition/data/dao/UserProfileDao;", "(Lcom/nhom10/aifitnutrition/data/dao/FoodLogDao;Lcom/nhom10/aifitnutrition/data/dao/WaterLogDao;Lcom/nhom10/aifitnutrition/data/dao/UserProfileDao;)V", "deleteFoodLog", "", "foodLog", "Lcom/nhom10/aifitnutrition/data/model/FoodLog;", "(Lcom/nhom10/aifitnutrition/data/model/FoodLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWaterLog", "waterLog", "Lcom/nhom10/aifitnutrition/data/model/WaterLog;", "(Lcom/nhom10/aifitnutrition/data/model/WaterLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFoodLogsByDate", "Lkotlinx/coroutines/flow/Flow;", "", "date", "", "getFoodLogsByMeal", "mealType", "getTotalCaloriesForDate", "", "getTotalCarbsForDate", "", "getTotalFatForDate", "getTotalProteinForDate", "getTotalWaterForDate", "getUserProfile", "Lcom/nhom10/aifitnutrition/data/model/UserProfile;", "getUserProfileOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWaterLogsByDate", "getWeeklyCalories", "Lcom/nhom10/aifitnutrition/data/dao/DateCalories;", "startDate", "insertFoodLog", "", "insertWaterLog", "saveUserProfile", "profile", "(Lcom/nhom10/aifitnutrition/data/model/UserProfile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFoodLog", "updateUserProfile", "app_debug"})
public final class NutritionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.dao.FoodLogDao foodLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.dao.WaterLogDao waterLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.dao.UserProfileDao userProfileDao = null;
    
    public NutritionRepository(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.dao.FoodLogDao foodLogDao, @org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.dao.WaterLogDao waterLogDao, @org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.dao.UserProfileDao userProfileDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertFoodLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.FoodLog foodLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFoodLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.FoodLog foodLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteFoodLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.FoodLog foodLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> getFoodLogsByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalCaloriesForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Float> getTotalProteinForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Float> getTotalCarbsForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Float> getTotalFatForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.dao.DateCalories>> getWeeklyCalories(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> getFoodLogsByMeal(@org.jetbrains.annotations.NotNull()
    java.lang.String mealType, @org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertWaterLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WaterLog waterLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteWaterLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WaterLog waterLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalWaterForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.WaterLog>> getWaterLogsByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.nhom10.aifitnutrition.data.model.UserProfile> getUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserProfileOnce(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nhom10.aifitnutrition.data.model.UserProfile> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserProfile(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUserProfile(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}