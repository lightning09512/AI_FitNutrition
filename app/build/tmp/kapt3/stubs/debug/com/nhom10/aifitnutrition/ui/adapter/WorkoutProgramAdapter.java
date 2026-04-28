package com.nhom10.aifitnutrition.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0014\u0015B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\n\u001a\u00020\u00062\n\u0010\u000b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\tR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/adapter/WorkoutProgramAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/nhom10/aifitnutrition/ui/workout/WorkoutProgram;", "Lcom/nhom10/aifitnutrition/ui/adapter/WorkoutProgramAdapter$ViewHolder;", "onClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "selectedId", "", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSelected", "programId", "Companion", "ViewHolder", "app_debug"})
public final class WorkoutProgramAdapter extends androidx.recyclerview.widget.ListAdapter<com.nhom10.aifitnutrition.ui.workout.WorkoutProgram, com.nhom10.aifitnutrition.ui.adapter.WorkoutProgramAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.nhom10.aifitnutrition.ui.workout.WorkoutProgram, kotlin.Unit> onClick = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String selectedId;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.nhom10.aifitnutrition.ui.workout.WorkoutProgram> DIFF = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.nhom10.aifitnutrition.ui.adapter.WorkoutProgramAdapter.Companion Companion = null;
    
    public WorkoutProgramAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.nhom10.aifitnutrition.ui.workout.WorkoutProgram, kotlin.Unit> onClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.nhom10.aifitnutrition.ui.adapter.WorkoutProgramAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.nhom10.aifitnutrition.ui.adapter.WorkoutProgramAdapter.ViewHolder holder, int position) {
    }
    
    public final void setSelected(@org.jetbrains.annotations.Nullable()
    java.lang.String programId) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/adapter/WorkoutProgramAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/nhom10/aifitnutrition/ui/workout/WorkoutProgram;", "getDIFF", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.nhom10.aifitnutrition.ui.workout.WorkoutProgram> getDIFF() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/nhom10/aifitnutrition/ui/adapter/WorkoutProgramAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/nhom10/aifitnutrition/databinding/ItemWorkoutProgramBinding;", "(Lcom/nhom10/aifitnutrition/ui/adapter/WorkoutProgramAdapter;Lcom/nhom10/aifitnutrition/databinding/ItemWorkoutProgramBinding;)V", "getBinding", "()Lcom/nhom10/aifitnutrition/databinding/ItemWorkoutProgramBinding;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.nhom10.aifitnutrition.databinding.ItemWorkoutProgramBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.nhom10.aifitnutrition.databinding.ItemWorkoutProgramBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.nhom10.aifitnutrition.databinding.ItemWorkoutProgramBinding getBinding() {
            return null;
        }
    }
}