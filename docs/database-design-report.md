## Database Schema

-- ============================================================
-- DATABASE DESIGN REPORT: AI FitNutrition
-- Framework: Android Room ORM + SQLite
-- Architecture: MVVM with Repository Pattern
-- Description: Cấu trúc cơ sở dữ liệu cho ứng dụng Fitness & Nutrition
-- ============================================================

-- 1. Entity: UserProfile (Quản lý thông tin cá nhân và mục tiêu sức khỏe)
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey
    val id: Int = 1,  // Single user architecture
    val name: String = "",
    val ageYears: Int = 25,
    val heightCm: Float = 170f,
    val weightKg: Float = 70f,
    val goalType: String = "maintain",  // lose | gain | maintain
    val targetWeightKg: Float = 70f,
    val dailyCalorieGoal: Int = 2000,
    val proteinGoalG: Float = 150f,
    val carbsGoalG: Float = 200f,
    val fatGoalG: Float = 65f,
    val waterGoalMl: Int = 2500,
    val activityLevel: String = "moderate",  // sedentary | light | moderate | active | very_active
    val gender: String = "male"
)

-- 2. Entity: FoodLogs (Nhật ký ăn uống và dinh dưỡng)
@Entity(tableName = "food_logs")
data class FoodLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mealType: String,        // breakfast, lunch, dinner, snack
    val foodName: String,
    val calories: Int,
    val proteinG: Float,
    val carbsG: Float,
    val fatG: Float,
    val portionGrams: Float = 100f,
    val imageUri: String? = null,
    val date: String,            // ISO date "2026-04-08"
    val timestamp: Long = System.currentTimeMillis(),
    val isAiAnalyzed: Boolean = false
)

-- 3. Entity: WorkoutLogs (Nhật ký tập luyện và hoạt động thể chất)
@Entity(tableName = "workout_logs")
data class WorkoutLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workoutType: String,     // Running, Walking, Cycling, Swimming, Gym, Yoga, etc.
    val durationMinutes: Int,
    val caloriesBurned: Int,
    val intensity: String = "Moderate",  // Light, Moderate, Intense
    val notes: String? = null,
    val date: String,
    val timestamp: Long = System.currentTimeMillis()
)

-- 4. Entity: WaterLogs (Nhật ký uống nước)
@Entity(tableName = "water_logs")
data class WaterLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amountMl: Int,
    val date: String,
    val timestamp: Long = System.currentTimeMillis()
)

-- 5. Entity: WeightLogs (Nhật ký cân nặng)
@Entity(tableName = "weight_logs")
data class WeightLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val weightKg: Float,
    val date: String,
    val timestamp: Long = System.currentTimeMillis()
)

-- 6. Entity: ChatMessages (Lịch sử trò chuyện với AI Coach)
@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val role: String,            // "user" | "model"
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)

-- ========================================
-- DATA ACCESS OBJECTS (DAOs)
-- ========================================

-- UserProfile DAO
@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getProfile(): UserProfile?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)
    
    @Update
    suspend fun updateProfile(profile: UserProfile)
}

-- FoodLogs DAO
@Dao
interface FoodLogDao {
    @Query("SELECT * FROM food_logs ORDER BY timestamp DESC")
    fun getAllFoodLogs(): Flow<List<FoodLog>>
    
    @Query("SELECT * FROM food_logs WHERE date = :date ORDER BY timestamp DESC")
    suspend fun getFoodLogsByDate(date: String): List<FoodLog>
    
    @Insert
    suspend fun insertFoodLog(foodLog: FoodLog)
    
    @Query("DELETE FROM food_logs WHERE id = :id")
    suspend fun deleteFoodLog(id: Long)
    
    @Query("SELECT SUM(calories) FROM food_logs WHERE date = :date")
    suspend fun getDailyCalories(date: String): Int?
}

-- WorkoutLogs DAO
@Dao
interface WorkoutLogDao {
    @Query("SELECT * FROM workout_logs ORDER BY timestamp DESC")
    fun getAllWorkoutLogs(): Flow<List<WorkoutLog>>
    
    @Query("SELECT * FROM workout_logs WHERE date = :date ORDER BY timestamp DESC")
    suspend fun getWorkoutLogsByDate(date: String): List<WorkoutLog>
    
    @Insert
    suspend fun insertWorkoutLog(workoutLog: WorkoutLog)
    
    @Query("DELETE FROM workout_logs WHERE id = :id")
    suspend fun deleteWorkoutLog(id: Long)
    
    @Query("SELECT SUM(caloriesBurned) FROM workout_logs WHERE date = :date")
    suspend fun getDailyCaloriesBurned(date: String): Int?
}

-- WaterLogs DAO
@Dao
interface WaterLogDao {
    @Query("SELECT * FROM water_logs ORDER BY timestamp DESC")
    fun getAllWaterLogs(): Flow<List<WaterLog>>
    
    @Query("SELECT * FROM water_logs WHERE date = :date ORDER BY timestamp DESC")
    suspend fun getWaterLogsByDate(date: String): List<WaterLog>
    
    @Insert
    suspend fun insertWaterLog(waterLog: WaterLog)
    
    @Query("SELECT SUM(amountMl) FROM water_logs WHERE date = :date")
    suspend fun getDailyWaterIntake(date: String): Int?
}

-- WeightLogs DAO
@Dao
interface WeightLogDao {
    @Query("SELECT * FROM weight_logs ORDER BY timestamp DESC")
    fun getAllWeightLogs(): Flow<List<WeightLog>>
    
    @Insert
    suspend fun insertWeightLog(weightLog: WeightLog)
    
    @Query("SELECT weightKg FROM weight_logs WHERE date = :date LIMIT 1")
    suspend fun getWeightByDate(date: String): Float?
}

-- ChatMessages DAO
@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<ChatMessage>>
    
    @Query("SELECT * FROM chat_messages ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentMessages(limit: Int): List<ChatMessage>
    
    @Insert
    suspend fun insertMessage(message: ChatMessage)
    
    @Query("DELETE FROM chat_messages")
    suspend fun clearHistory()
}

-- ========================================
-- REPOSITORY LAYER
-- ========================================

-- Nutrition Repository
class NutritionRepository(
    private val foodLogDao: FoodLogDao,
    private val waterLogDao: WaterLogDao,
    private val userProfileDao: UserProfileDao
) {
    fun getAllFoodLogs() = foodLogDao.getAllFoodLogs()
    suspend fun insertFoodLog(foodLog: FoodLog) = foodLogDao.insertFoodLog(foodLog)
    suspend fun getDailyNutrition(date: String) = foodLogDao.getFoodLogsByDate(date)
    suspend fun getDailyWater(date: String) = waterLogDao.getDailyWaterIntake(date)
    suspend fun getUserProfile() = userProfileDao.getProfile()
}

-- Fitness Repository  
class FitnessRepository(
    private val workoutLogDao: WorkoutLogDao,
    private val weightLogDao: WeightLogDao
) {
    fun getAllWorkoutLogs() = workoutLogDao.getAllWorkoutLogs()
    suspend fun insertWorkoutLog(workoutLog: WorkoutLog) = workoutLogDao.insertWorkoutLog(workoutLog)
    suspend fun getDailyWorkouts(date: String) = workoutLogDao.getWorkoutLogsByDate(date)
    fun getAllWeightLogs() = weightLogDao.getAllWeightLogs()
    suspend fun insertWeightLog(weightLog: WeightLog) = weightLogDao.insertWeightLog(weightLog)
}

-- Chat Repository
class ChatRepository(private val chatMessageDao: ChatMessageDao) {
    fun getAllMessages() = chatMessageDao.getAllMessages()
    suspend fun insertMessage(message: ChatMessage) = chatMessageDao.insertMessage(message)
    suspend fun getRecentMessages(limit: Int) = chatMessageDao.getRecentMessages(limit)
    suspend fun clearHistory() = chatMessageDao.clearHistory()
}

-- ========================================
-- DATABASE CONFIGURATION
-- ========================================

@Database(
    entities = [
        UserProfile::class,
        FoodLog::class,
        WorkoutLog::class,
        WaterLog::class,
        WeightLog::class,
        ChatMessage::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun foodLogDao(): FoodLogDao
    abstract fun workoutLogDao(): WorkoutLogDao
    abstract fun waterLogDao(): WaterLogDao
    abstract fun weightLogDao(): WeightLogDao
    abstract fun chatMessageDao(): ChatMessageDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "aifitnutrition_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

-- ========================================
-- RELATIONSHIPS & ARCHITECTURE
-- ========================================

-- UserProfile là entity trung tâm (Single User Architecture)
-- Mối quan hệ One-to-Many với tất cả log entities
-- Sử dụng Room ORM cho type-safe database operations
-- Repository pattern cho separation of concerns
-- Flow/LiveData cho reactive data streams

-- Data Flow:
-- UI Layer (Fragment/Activity) 
//     ↓
// ViewModel Layer
//     ↓  
// Repository Layer
//     ↓
// DAO Layer
//     ↓
// Room Database
//     ↓
// SQLite Database

-- ========================================
-- PERFORMANCE OPTIMIZATIONS
-- ========================================

-- Room tự động tạo indexes cho primary keys
-- Sử dụng Flow cho reactive queries
-- Coroutines cho background operations
-- Proper data types cho storage efficiency
-- Single user architecture giảm complexity
