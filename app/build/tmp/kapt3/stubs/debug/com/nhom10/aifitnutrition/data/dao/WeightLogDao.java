package com.nhom10.aifitnutrition.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\r\u001a\u00020\u000eH\'J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/nhom10/aifitnutrition/data/dao/WeightLogDao;", "", "delete", "", "weightLog", "Lcom/nhom10/aifitnutrition/data/model/WeightLog;", "(Lcom/nhom10/aifitnutrition/data/model/WeightLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWeightLogs", "Lkotlinx/coroutines/flow/Flow;", "", "getLatestWeight", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWeightLogsFromDate", "startDate", "", "insert", "", "app_debug"})
@androidx.room.Dao()
public abstract interface WeightLogDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WeightLog weightLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.data.model.WeightLog weightLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM weight_logs ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.WeightLog>> getAllWeightLogs();
    
    @androidx.room.Query(value = "SELECT * FROM weight_logs ORDER BY timestamp DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestWeight(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.nhom10.aifitnutrition.data.model.WeightLog> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM weight_logs WHERE date >= :startDate ORDER BY timestamp ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.nhom10.aifitnutrition.data.model.WeightLog>> getWeightLogsFromDate(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate);
}