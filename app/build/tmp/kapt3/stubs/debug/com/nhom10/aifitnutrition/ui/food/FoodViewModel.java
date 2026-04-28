package com.nhom10.aifitnutrition.ui.food;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u0006\u0010\'\u001a\u00020$J\u0006\u0010(\u001a\u00020$J\u000e\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020\u0010J\u000e\u0010+\u001a\u00020$2\u0006\u0010*\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\rR\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\rR\u0019\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\rR\u0011\u0010 \u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u00a8\u0006-"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/food/FoodViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_analysisState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/nhom10/aifitnutrition/ui/food/FoodAnalysisState;", "_snackbarMessage", "", "analysisState", "Landroidx/lifecycle/LiveData;", "getAnalysisState", "()Landroidx/lifecycle/LiveData;", "breakfastLogs", "", "Lcom/nhom10/aifitnutrition/data/model/FoodLog;", "getBreakfastLogs", "db", "Lcom/nhom10/aifitnutrition/data/database/AppDatabase;", "dinnerLogs", "getDinnerLogs", "geminiService", "Lcom/nhom10/aifitnutrition/ai/GeminiService;", "lunchLogs", "getLunchLogs", "repo", "Lcom/nhom10/aifitnutrition/data/repository/NutritionRepository;", "snackLogs", "getSnackLogs", "snackbarMessage", "getSnackbarMessage", "today", "getToday", "()Ljava/lang/String;", "analyzeFoodImage", "", "bitmap", "Landroid/graphics/Bitmap;", "clearAnalysisState", "clearSnackbar", "deleteFoodLog", "foodLog", "insertFoodLog", "Factory", "app_debug"})
public final class FoodViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.database.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.repository.NutritionRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.ai.GeminiService geminiService = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String today = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> breakfastLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> lunchLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> dinnerLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> snackLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.nhom10.aifitnutrition.ui.food.FoodAnalysisState> _analysisState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.nhom10.aifitnutrition.ui.food.FoodAnalysisState> analysisState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _snackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> snackbarMessage = null;
    
    public FoodViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> getBreakfastLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> getLunchLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> getDinnerLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.nhom10.aifitnutrition.data.model.FoodLog>> getSnackLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.nhom10.aifitnutrition.ui.food.FoodAnalysisState> getAnalysisState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSnackbarMessage() {
        return null;
    }
    
    public final void analyzeFoodImage(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
    }
    
    public final void insertFoodLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.FoodLog foodLog) {
    }
    
    public final void deleteFoodLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.FoodLog foodLog) {
    }
    
    public final void clearAnalysisState() {
    }
    
    public final void clearSnackbar() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/food/FoodViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
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