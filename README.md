# AI FitNutrition

Ứng dụng Android hỗ trợ theo dõi sức khỏe cá nhân với trọng tâm **dinh dưỡng + tập luyện + tư vấn AI**.  
Project được xây dựng theo hướng thực tế cho người dùng hằng ngày: ghi log bữa ăn, theo dõi chỉ số cơ thể, gợi ý bài tập và trò chuyện với AI bằng tiếng Việt.

## Mục tiêu dự án

- Hỗ trợ người dùng quản lý sức khỏe trong một ứng dụng duy nhất.
- Kết hợp AI để tư vấn dinh dưỡng/tập luyện theo ngữ cảnh cá nhân.
- Tạo trải nghiệm trực quan: biểu đồ tiến độ, camera phân tích món ăn, chương trình tập có đếm giờ.

## Tính năng chính

### 1) Dashboard tổng quan
- Hiển thị calo nạp vào, calo đốt, calo còn lại trong ngày.
- Theo dõi macro (protein, carb, fat) và lượng nước.
- Hiển thị tóm tắt tiến độ theo tuần.

### 2) Dinh dưỡng (Food Log)
- Ghi bữa ăn theo nhóm: sáng, trưa, tối, snack.
- Nhập tay món ăn và macro.
- Mở camera để chụp ảnh món ăn và phân tích bằng AI.

### 3) AI Coach (Chat)
- Trò chuyện trực tiếp với trợ lý AI `FitBot`.
- Prompt và phản hồi tối ưu cho tiếng Việt.
- Có cơ chế fallback provider AI (Gemini/OpenRouter) khi một bên lỗi.
- Lưu lịch sử hội thoại trong local database.

### 4) Tập luyện (Workout)
- Ghi log buổi tập: loại bài, thời lượng, cường độ, calo đốt.
- Danh sách chương trình tập mẫu (full body, core, chest/arms, legs...).
- Màn hình chạy bài tập theo từng bước, đếm ngược tự động từng bài.
- Hỗ trợ tải dữ liệu bài tập từ `CSV` trong assets.

### 5) Hồ sơ & theo dõi cơ thể
- Lưu thông tin cá nhân: tên, tuổi, chiều cao, cân nặng, mục tiêu.
- Ghi cân nặng theo ngày.
- Biểu đồ cân nặng 30 ngày.
- Xuất báo cáo PDF sức khỏe.

### 6) Đa ngôn ngữ
- Hỗ trợ tiếng Việt và tiếng Anh.
- Có thể đổi ngôn ngữ trực tiếp trong màn Hồ sơ.

### 7) Nhắc nhở định kỳ
- Nhắc uống nước và bữa ăn bằng WorkManager.

## Công nghệ sử dụng

- **Ngôn ngữ:** Kotlin
- **UI:** XML + ViewBinding + Material Components
- **Kiến trúc:** MVVM (ViewModel + Repository + Room)
- **Database:** Room
- **Bất đồng bộ:** Kotlin Coroutines + LiveData/Flow
- **AI SDK:** Google Generative AI (Gemini)
- **AI fallback:** OpenRouter (HTTP API)
- **Khác:** CameraX, MPAndroidChart, iText PDF, WorkManager, Glide

## Cấu trúc thư mục chính

- `app/src/main/java/com/nhom10/aifitnutrition/ui`  
  Chứa các màn hình: dashboard, food, workout, chat, profile...
- `app/src/main/java/com/nhom10/aifitnutrition/data`  
  Model, DAO, Room database, repository.
- `app/src/main/java/com/nhom10/aifitnutrition/ai`  
  Tích hợp dịch vụ AI (Gemini/OpenRouter).
- `app/src/main/res/layout`  
  XML giao diện.
- `app/src/main/assets/exercise_library.csv`  
  Dữ liệu bài tập từ CSV.

## Yêu cầu môi trường

- Android Studio (phiên bản mới, hỗ trợ AGP 8+)
- JDK 17
- Android SDK (theo `compileSdk` trong `app/build.gradle.kts`)

## Cài đặt & chạy project

1. Clone project và mở bằng Android Studio.
2. Tạo/cập nhật file `local.properties` ở thư mục gốc:

```properties
GEMINI_API_KEY=your_gemini_key
OPENROUTER_API_KEY=your_openrouter_key
sdk.dir=YOUR_ANDROID_SDK_PATH
```

3. Chọn **Sync Project with Gradle Files**.
4. Build và chạy app trên emulator/thiết bị thật.

## Cấu hình dữ liệu bài tập (CSV)

App hỗ trợ đọc dữ liệu bài tập từ file CSV trong assets.  
Mẫu cột dữ liệu:

```csv
exercise_id,name_vi,name_en,category,difficulty,default_duration_sec,default_reps,rest_sec,equipment,primary_muscle,instructions_vi,instructions_en,common_mistakes_vi,common_mistakes_en,safety_note_vi,safety_note_en,video_url,gif_url,thumbnail_url
```

Lưu ý:
- Nếu dùng ảnh/GIF local, đặt file trong `app/src/main/assets/...` và dùng đúng path trong CSV.
- Nếu dùng URL online, đảm bảo URL truy cập được từ thiết bị chạy app.

## Quyền truy cập ứng dụng

- `INTERNET`: gọi AI API.
- `CAMERA`: chụp món ăn để phân tích.
- `POST_NOTIFICATIONS`: nhắc lịch uống nước/bữa ăn.
- Một số quyền media/storage được khai báo theo phiên bản Android.

## Hướng phát triển tiếp theo

- Hoàn thiện workout player nâng cao (rest timer giữa bài, resume session).
- Cải thiện UX chat (composer, auto-scroll tốt hơn khi mở bàn phím).
- Thêm lớp bảo mật key tốt hơn (proxy backend thay vì để key ở client).
- Bổ sung test tự động cho luồng AI và workout.

---

Nếu bạn cần, có thể mở issue theo mẫu:
- Màn hình/flow gặp lỗi
- Log lỗi ngắn
- Thiết bị Android + phiên bản OS
- Ảnh chụp màn hình
