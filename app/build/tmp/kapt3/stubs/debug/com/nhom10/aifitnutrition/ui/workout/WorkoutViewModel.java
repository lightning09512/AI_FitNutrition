package com.nhom10.aifitnutrition.ui.workout;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u000fJ&\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u00072\u0006\u0010\'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u000fR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0016\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0011R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/workout/WorkoutViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_snackbarMessage", "Landroidx/lifecycle/MutableLiveData;", "", "db", "Lcom/nhom10/aifitnutrition/data/database/AppDatabase;", "nutritionRepo", "Lcom/nhom10/aifitnutrition/data/repository/NutritionRepository;", "recentWorkouts", "Landroidx/lifecycle/LiveData;", "", "Lcom/nhom10/aifitnutrition/data/model/WorkoutLog;", "getRecentWorkouts", "()Landroidx/lifecycle/LiveData;", "repo", "Lcom/nhom10/aifitnutrition/data/repository/FitnessRepository;", "snackbarMessage", "getSnackbarMessage", "today", "getToday", "()Ljava/lang/String;", "totalCaloriesBurnedToday", "", "getTotalCaloriesBurnedToday", "userProfile", "Lcom/nhom10/aifitnutrition/data/model/UserProfile;", "getUserProfile", "workoutsToday", "getWorkoutsToday", "clearSnackbar", "", "deleteWorkout", "workoutLog", "estimateCalories", "type", "duration", "intensity", "weightKg", "", "insertWorkout", "Factory", "app_debug"})
public final class WorkoutViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.database.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.repository.FitnessRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.repository.NutritionRepository nutritionRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String today = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.WorkoutLog>> workoutsToday = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalCaloriesBurnedToday = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.WorkoutLog>> recentWorkouts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.nhom10.aifitnutrition.data.model.UserProfile> userProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _snackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> snackbarMessage = null;
    
    public WorkoutViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.WorkoutLog>> getWorkoutsToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalCaloriesBurnedToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.WorkoutLog>> getRecentWorkouts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.nhom10.aifitnutrition.data.model.UserProfile> getUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSnackbarMessage() {
        return null;
    }
    
    public final void insertWorkout(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WorkoutLog workoutLog) {
    }
    
    public final void deleteWorkout(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WorkoutLog workoutLog) {
    }
    
    public final int estimateCalories(@org.jetbrains.annotations.NotNull()
    java.lang.String type, int duration, @org.jetbrains.annotations.NotNull()
    java.lang.String intensity, float weightKg) {
        return 0;
    }
    
    public final void clearSnackbar() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/workout/WorkoutViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
    public static final class Factory implements androidx.lifecycle.ViewModelProvider.Factory {
        @org.jetbrains.annotations.NotNull()
        private final android.app.Application application = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        android.app.Application application) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
        java.lang.Class<T> modelClass) {
            return null;
        }
    }
}