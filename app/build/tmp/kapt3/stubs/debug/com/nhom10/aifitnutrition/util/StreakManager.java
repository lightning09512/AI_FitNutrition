package com.nhom10.aifitnutrition.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000f\u001a\u00020\u0004H\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/nhom10/aifitnutrition/util/StreakManager;", "", "()V", "KEY_LAST_ACTIVE_DATE", "", "KEY_STREAK_COUNT", "PREFS_NAME", "extendStreak", "", "context", "Landroid/content/Context;", "getPrefs", "Landroid/content/SharedPreferences;", "getStreak", "", "getTodayDateString", "getYesterdayDateString", "app_debug"})
public final class StreakManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "streak_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_STREAK_COUNT = "streak_count";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_ACTIVE_DATE = "last_active_date";
    @org.jetbrains.annotations.NotNull()
    public static final com.nhom10.aifitnutrition.util.StreakManager INSTANCE = null;
    
    private StreakManager() {
        super();
    }
    
    private final android.content.SharedPreferences getPrefs(android.content.Context context) {
        return null;
    }
    
    private final java.lang.String getTodayDateString() {
        return null;
    }
    
    private final java.lang.String getYesterdayDateString() {
        return null;
    }
    
    public final int getStreak(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    /**
     * Call this when the user performs an activity (logs food, water, or workout).
     * @return true if the streak was just extended today (to trigger confetti).
     */
    public final boolean extendStreak(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}