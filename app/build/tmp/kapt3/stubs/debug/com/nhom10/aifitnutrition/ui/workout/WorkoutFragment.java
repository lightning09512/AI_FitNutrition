package com.nhom10.aifitnutrition.ui.workout;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nH\u0002J$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001aH\u0016J\u001a\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010\'\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nH\u0002J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u000eH\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u000eH\u0002J\b\u0010,\u001a\u00020\u001aH\u0002J\b\u0010-\u001a\u00020\u001aH\u0002J\b\u0010.\u001a\u00020\u001aH\u0002J\b\u0010/\u001a\u00020\u001aH\u0002J\b\u00100\u001a\u00020\u001aH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/workout/WorkoutFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/nhom10/aifitnutrition/databinding/FragmentWorkoutBinding;", "binding", "getBinding", "()Lcom/nhom10/aifitnutrition/databinding/FragmentWorkoutBinding;", "loadedPrograms", "", "Lcom/nhom10/aifitnutrition/ui/workout/WorkoutProgram;", "programAdapter", "Lcom/nhom10/aifitnutrition/ui/adapter/WorkoutProgramAdapter;", "selectedIntensity", "", "selectedProgram", "selectedWorkoutType", "viewModel", "Lcom/nhom10/aifitnutrition/ui/workout/WorkoutViewModel;", "getViewModel", "()Lcom/nhom10/aifitnutrition/ui/workout/WorkoutViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "workoutLogAdapter", "Lcom/nhom10/aifitnutrition/ui/adapter/WorkoutAdapter;", "applyProgramToInputs", "", "program", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "openProgramPlayer", "setIntensityChip", "intensity", "setWorkoutChip", "type", "setupChips", "setupObservers", "setupPrograms", "setupSlider", "updateEstimate", "app_debug"})
public final class WorkoutFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.nhom10.aifitnutrition.databinding.FragmentWorkoutBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nhom10.aifitnutrition.ui.adapter.WorkoutAdapter workoutLogAdapter = null;
    private com.nhom10.aifitnutrition.ui.adapter.WorkoutProgramAdapter programAdapter;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String selectedWorkoutType = "Running";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String selectedIntensity = "Moderate";
    @org.jetbrains.annotations.Nullable()
    private com.nhom10.aifitnutrition.ui.workout.WorkoutProgram selectedProgram;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.nhom10.aifitnutrition.ui.workout.WorkoutProgram> loadedPrograms;
    
    public WorkoutFragment() {
        super();
    }
    
    private final com.nhom10.aifitnutrition.databinding.FragmentWorkoutBinding getBinding() {
        return null;
    }
    
    private final com.nhom10.aifitnutrition.ui.workout.WorkoutViewModel getViewModel() {
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
    
    private final void setupPrograms() {
    }
    
    private final void setupChips() {
    }
    
    private final void setupSlider() {
    }
    
    private final void applyProgramToInputs(com.nhom10.aifitnutrition.ui.workout.WorkoutProgram program) {
    }
    
    private final void setWorkoutChip(java.lang.String type) {
    }
    
    private final void setIntensityChip(java.lang.String intensity) {
    }
    
    private final void openProgramPlayer(com.nhom10.aifitnutrition.ui.workout.WorkoutProgram program) {
    }
    
    private final void updateEstimate() {
    }
    
    private final void setupObservers() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}