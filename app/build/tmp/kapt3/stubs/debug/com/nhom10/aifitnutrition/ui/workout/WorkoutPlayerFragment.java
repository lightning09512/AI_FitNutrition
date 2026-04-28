package com.nhom10.aifitnutrition.ui.workout;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\fH\u0002J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\u0017H\u0016J\u001a\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010+\u001a\u00020\u0017H\u0002J\b\u0010,\u001a\u00020\u0017H\u0002J\b\u0010-\u001a\u00020\u0017H\u0002J\u0010\u0010.\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\fH\u0002J\u0010\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020\tH\u0002J\u0010\u00101\u001a\u00020\u00172\u0006\u00100\u001a\u00020\tH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/workout/WorkoutPlayerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/nhom10/aifitnutrition/databinding/FragmentWorkoutPlayerBinding;", "binding", "getBinding", "()Lcom/nhom10/aifitnutrition/databinding/FragmentWorkoutPlayerBinding;", "currentIndex", "", "exercises", "", "Lcom/nhom10/aifitnutrition/ui/workout/WorkoutExercise;", "getExercises", "()Ljava/util/List;", "isPaused", "", "program", "Lcom/nhom10/aifitnutrition/ui/workout/WorkoutProgram;", "remainingSec", "timer", "Landroid/os/CountDownTimer;", "bindExercise", "", "index", "restart", "finishWorkout", "getExerciseInstructions", "", "exerciseName", "loadExerciseImage", "exercise", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "pauseTimer", "resumeTimer", "setupActions", "showExerciseInstructions", "startTimer", "seconds", "updateTimerLabel", "app_debug"})
public final class WorkoutPlayerFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.nhom10.aifitnutrition.databinding.FragmentWorkoutPlayerBinding _binding;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer timer;
    private boolean isPaused = false;
    private int currentIndex = 0;
    private int remainingSec = 0;
    @org.jetbrains.annotations.Nullable()
    private com.nhom10.aifitnutrition.ui.workout.WorkoutProgram program;
    
    public WorkoutPlayerFragment() {
        super();
    }
    
    private final com.nhom10.aifitnutrition.databinding.FragmentWorkoutPlayerBinding getBinding() {
        return null;
    }
    
    private final java.util.List<com.nhom10.aifitnutrition.ui.workout.WorkoutExercise> getExercises() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupActions() {
    }
    
    private final void bindExercise(int index, boolean restart) {
    }
    
    private final void loadExerciseImage(com.nhom10.aifitnutrition.ui.workout.WorkoutExercise exercise) {
    }
    
    private final void startTimer(int seconds) {
    }
    
    private final void pauseTimer() {
    }
    
    private final void resumeTimer() {
    }
    
    private final void updateTimerLabel(int seconds) {
    }
    
    private final void showExerciseInstructions(com.nhom10.aifitnutrition.ui.workout.WorkoutExercise exercise) {
    }
    
    private final java.lang.String getExerciseInstructions(java.lang.String exerciseName) {
        return null;
    }
    
    private final void finishWorkout() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}