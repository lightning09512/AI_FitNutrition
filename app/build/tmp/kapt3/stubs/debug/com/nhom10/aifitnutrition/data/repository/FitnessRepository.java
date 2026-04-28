package com.nhom10.aifitnutrition.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00120\u0011J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\u0011J\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00112\u0006\u0010\u0018\u001a\u00020\u0019J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00120\u00112\u0006\u0010\u001b\u001a\u00020\u0019J\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00120\u00112\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/nhom10/aifitnutrition/data/repository/FitnessRepository;", "", "workoutLogDao", "Lcom/nhom10/aifitnutrition/data/dao/WorkoutLogDao;", "weightLogDao", "Lcom/nhom10/aifitnutrition/data/dao/WeightLogDao;", "(Lcom/nhom10/aifitnutrition/data/dao/WorkoutLogDao;Lcom/nhom10/aifitnutrition/data/dao/WeightLogDao;)V", "deleteWeightLog", "", "weightLog", "Lcom/nhom10/aifitnutrition/data/model/WeightLog;", "(Lcom/nhom10/aifitnutrition/data/model/WeightLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWorkout", "workoutLog", "Lcom/nhom10/aifitnutrition/data/model/WorkoutLog;", "(Lcom/nhom10/aifitnutrition/data/model/WorkoutLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWeightLogs", "Lkotlinx/coroutines/flow/Flow;", "", "getLatestWeight", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentWorkouts", "getTotalCaloriesBurnedForDate", "", "date", "", "getWeightLogsFromDate", "startDate", "getWorkoutsByDate", "insertWeightLog", "", "insertWorkout", "app_debug"})
public final class FitnessRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.dao.WorkoutLogDao workoutLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.data.dao.WeightLogDao weightLogDao = null;
    
    public FitnessRepository(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.dao.WorkoutLogDao workoutLogDao, @org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.dao.WeightLogDao weightLogDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertWorkout(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WorkoutLog workoutLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteWorkout(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WorkoutLog workoutLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.WorkoutLog>> getWorkoutsByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalCaloriesBurnedForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.WorkoutLog>> getRecentWorkouts() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertWeightLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WeightLog weightLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteWeightLog(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WeightLog weightLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.WeightLog>> getAllWeightLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLatestWeight(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nhom10.aifitnutrition.data.model.WeightLog> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.WeightLog>> getWeightLogsFromDate(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate) {
        return null;
    }
}