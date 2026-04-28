package com.nhom10.aifitnutrition.ui.workout

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.databinding.FragmentWorkoutPlayerBinding

class WorkoutPlayerFragment : Fragment() {

    private var _binding: FragmentWorkoutPlayerBinding? = null
    private val binding get() = _binding!!

    private var timer: CountDownTimer? = null
    private var isPaused = false
    private var currentIndex = 0
    private var remainingSec = 0

    private var program: WorkoutProgram? = null
    private val exercises get() = program?.exercises.orEmpty()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val programId = arguments?.getString("programId").orEmpty()
        program = WorkoutPrograms.findProgramById(programId)

        if (program == null || exercises.isEmpty()) {
            Snackbar.make(binding.root, "Khong tim thay chuong trinh!", Snackbar.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }

        binding.tvPlayerProgramTitle.text = program?.title
        bindExercise(index = 0, restart = true)
        setupActions()
    }

    private fun setupActions() {
        binding.btnPrevExercise.setOnClickListener {
            if (currentIndex > 0) bindExercise(currentIndex - 1, restart = true)
        }
        binding.btnNextExercise.setOnClickListener {
            if (currentIndex < exercises.lastIndex) {
                bindExercise(currentIndex + 1, restart = true)
            } else {
                finishWorkout()
            }
        }
        binding.btnPauseResume.setOnClickListener {
            if (isPaused) resumeTimer() else pauseTimer()
        }
        binding.btnFinishWorkout.setOnClickListener {
            finishWorkout()
        }
    }

    private fun bindExercise(index: Int, restart: Boolean) {
        currentIndex = index
        val exercise = exercises[index]
        val duration = exercise.durationSeconds.coerceAtLeast(10)

        binding.tvPlayerStep.text = "Bai ${index + 1}/${exercises.size}"
        binding.tvExerciseName.text = exercise.name
        binding.tvExerciseTarget.text = exercise.target
        binding.progressTimer.max = duration

        // ẩn hướng dẫn tập khi chuyển bài mới
        binding.tvExerciseInstructions.visibility = View.GONE
        
        loadExerciseImage(exercise)

        if (restart) startTimer(duration)
    }

    private fun loadExerciseImage(exercise: WorkoutExercise) {
        val mediaPath = exercise.mediaPath.trim()

        // Hien placeholder mac dinh truoc
        binding.layoutExercisePlaceholder.visibility = View.VISIBLE
        binding.ivExerciseMedia.setImageDrawable(null)

        if (mediaPath.isBlank()) return

        val source: Any = if (mediaPath.startsWith("http")) {
            mediaPath
        } else if (mediaPath.contains("exercise_library.csv")) {
            // Handle exercise_library.csv paths
            "file:///android_asset/exercise_library.csv/${mediaPath.substringAfter("exercise_library.csv/")}"
        } else {
            // Try to load from assets directly
            "file:///android_asset/exercise_library.csv/$mediaPath"
        }

        Glide.with(this)
            .load(source)
            .placeholder(com.nhom10.aifitnutrition.R.drawable.ic_workout)
            .error(com.nhom10.aifitnutrition.R.drawable.ic_workout)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    if (_binding != null) {
                        binding.layoutExercisePlaceholder.visibility = View.VISIBLE
                        // Hiển thị hướng dẫn tập bằng tiếng Việt khi không tải được ảnh
                        showExerciseInstructions(exercise)
                    }
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    if (_binding != null) {
                        binding.layoutExercisePlaceholder.visibility = View.GONE
                        // ẩn hướng dẫn tập khi ảnh tải thành công
                        binding.tvExerciseInstructions.visibility = View.GONE
                    }
                    return false
                }
            })
            .into(binding.ivExerciseMedia)
    }

    private fun startTimer(seconds: Int) {
        timer?.cancel()
        isPaused = false
        binding.btnPauseResume.text = "Tam dung"
        remainingSec = seconds
        binding.progressTimer.progress = seconds
        updateTimerLabel(remainingSec)

        timer = object : CountDownTimer(seconds * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                if (_binding == null) return
                remainingSec = (millisUntilFinished / 1000L).toInt().coerceAtLeast(0)
                binding.progressTimer.progress = remainingSec
                updateTimerLabel(remainingSec)
            }
            override fun onFinish() {
                if (_binding == null) return
                binding.progressTimer.progress = 0
                updateTimerLabel(0)
                if (currentIndex < exercises.lastIndex) {
                    bindExercise(currentIndex + 1, restart = true)
                } else {
                    finishWorkout()
                }
            }
        }.start()
    }

    private fun pauseTimer() {
        timer?.cancel()
        isPaused = true
        binding.btnPauseResume.text = "Tiep tuc"
    }

    private fun resumeTimer() {
        startTimer(remainingSec.coerceAtLeast(1))
    }

    private fun updateTimerLabel(seconds: Int) {
        binding.tvExerciseTimer.text = String.format("%02d:%02d", seconds / 60, seconds % 60)
    }

    private fun showExerciseInstructions(exercise: WorkoutExercise) {
        val instructions = getExerciseInstructions(exercise.name)
        binding.tvExerciseInstructions.text = instructions
        binding.tvExerciseInstructions.visibility = View.VISIBLE
    }

    private fun getExerciseInstructions(exerciseName: String): String {
        return when (exerciseName.lowercase()) {
            "bodyweight squat" -> "🏋️‍♂️ TẬP SQUAT KHÔNG TẠ\n\n1. Đứng thẳng, chân rộng bằng vai\n2. Hông đẩy ra sau như đang ngồi ghế\n3. Hạ người xuống cho đùi song song sàn\n4. Đầu gối không vượt quá mũi chân\n5. Đẩy mạnh gót chân để đứng dậy\n6. Siết chặt mông khi lên\n\n💡 Hít vào khi hạ, thở ra khi lên!"
            
            "push-up" -> "💪 TẬP HÍT ĐẤT (PUSH-UP)\n\n1. Chống tay rộng hơn vai một chút\n2. Thân người thẳng từ đầu đến gót\n3. Hạ ngực xuống gần sàn, khuỷu tay 45 độ\n4. Đẩy mạnh lên vị trí bắt đầu\n5. Siết cơ ngực và tay sau khi lên\n\n💡 Giữ core căng, không võng lưng!"
            
            "plank" -> "🧘 TẬP PLANK\n\n1. Chống cẳng tay, khuỷu thẳng dưới vai\n2. Thân người thẳng như một cái ván\n3. Siết chặt cơ bụng và cơ mông\n4. Đầu và cổ thẳng với cột sống\n5. Giữ nguyên vị trí, không rung\n\n💡 Hít thở đều, không nín thở!"
            
            "jumping jack" -> "🤸 TẬP JUMPING JACK\n\n1. Đứng thẳng, tay buông bên hông\n2. Nhảy bật hai chân ra sang ngang\n3. Đồng thời đưa hai tay qua đầu\n4. Nhảy trở về vị trí ban đầu\n5. Tiếp đất nhẹ nhàng, đầu gối hơi khuỵu\n\n💡 Thực hiện nhịp nhàng, không quá gắng!"
            
            "crunch" -> "🎯 TẬP GẬP BỤNG (CRUNCH)\n\n1. Nằm ngửa, gối co 90 độ\n2. Chân đặt rộng bằng hông\n3. Tay đặt sau đầu hoặc ngực\n4. Nâng vai lên khỏi sàn, gập bụng\n5. Cằm cách ngực một nắm tay\n6. Hạ xuống chậm, có kiểm soát\n\n💡 Không dùng đà, chỉ dùng cơ bụng!"
            
            "leg raise" -> "🦵 TẬP NÂNG CHÂN\n\n1. Nằm ngửa, tay dưới mông\n2. Chân duỗi thẳng, ghép vào nhau\n3. Nâng hai chân lên gần vuông góc\n4. Lưng dưới ép chặt xuống sàn\n5. Hạ chân xuống chậm, không chạm sàn\n\n💡 Siết cơ bụng dưới khi nâng chân!"
            
            "glute bridge" -> "🍑 TẬP GLUTE BRIDGE\n\n1. Nằm ngửa, gối co, bàn chân trên sàn\n2. Tay đặt dọc theo thân người\n3. Nâng hông lên thành đường thẳng\n4. Siết chặt cơ mông ở vị trí trên cùng\n5. Hạ hông xuống chậm, có kiểm soát\n\n💡 Đẩy gót chân để nâng hông cao hơn!"
            
            "mountain climber" -> "🏔️ TẬP MOUNTAIN CLIMBER\n\n1. Vị trí plank, tay thẳng\n2. Thân người thẳng, core căng\n3. Kéo một gối lên gần ngực\n4. Chuyển chân nhanh như chạy tại chỗ\n5. Giữ hông không nâng lên quá cao\n\n💡 Thực hiện nhanh nhưng vẫn kiểm soát!"
            
            "burpee" -> "🔥 TẬP BURPEE\n\n1. Đứng thẳng, chân rộng bằng vai\n2. Hạ người xuống vị trí squat\n3. Chống tay xuống sàn\n4. Nhảy chân ra sau thành plank\n5. Nhảy chân vào lại vị trí squat\n6. Nhảy bật lên cao, vỗ tay trên đầu\n\n💡 Thực hiện liên tục, giữ nhịp độ!"
            
            "lunge" -> "🏃 TẬP LUNGE\n\n1. Đứng thẳng, chân rộng bằng hông\n2. Bước một chân về trước\n3. Hạ người xuống, cả hai gối 90 độ\n4. Đầu gối trước không vượt quá mũi chân\n5. Đẩy mạnh lên vị trí bắt đầu\n6. Đổi chân và lặp lại\n\n💡 Giữ thẳng lưng, không ngả người về trước!"
            
            "reverse lunge" -> "🏃 TẬP LUNGE NGƯỢC\n\n1. Đứng thẳng, chân rộng bằng hông\n2. Bước một chân lùi ra sau\n3. Hạ gối sau gần chạm sàn\n4. Gối trước 90 độ, không vượt mũi chân\n5. Đẩy mạnh lên vị trí bắt đầu\n6. Đổi chân và lặp lại\n\n💡 Giữ thân thẳng và kiểm soát đầu gối trước!"
            
            "bicycle crunch" -> "🚴 TẬP GẬP BỤNG ĐẠP XE\n\n1. Nằm ngửa, tay đặt sau đầu\n2. Nâng vai khỏi sàn, co một gối\n3. Chạm khuỷu tay phải vào gối trái\n4. Duỗi thẳng chân phải đồng thời\n5. Đổi bên, luân phiên nhịp nhàng\n6. Thở ra khi gập, hít vào khi duỗi\n\n💡 Không kéo cổ, chỉ dùng cơ bụng!"
            
            "russian twist" -> "🔄 TẬP VẬN BỤNG NGA\n\n1. Ngồi ngả nhẹ người về sau\n2. Nâng chân lên hoặc giữ gót trên sàn\n3. Tay nắm trước ngực, xoay thân sang phải\n4. Xoay sang trái, luân phiên nhịp nhàng\n5. Giữ lưng thẳng, core căng\n6. Thở ra khi xoay, hít vào khi về giữa\n\n💡 Xoay từ core, không chỉ vung tay!"
            
            "side plank" -> "🧘‍♀️ TẬP PLANK NGHIÊNG\n\n1. Nằm nghiêng, chống một cẳng tay\n2. Nâng hông lên thành đường thẳng\n3. Chân chồng lên nhau hoặc xếp lên sàn\n4. Siết chặt cơ bụng và mông\n5. Giữ thăng bằng, không đổ người\n6. Giữ nguyên vị trí, hít thở đều\n\n💡 Đổi bên đều nhau mỗi hiệp!"
            
            "dead bug" -> "🐛 TẬP DEAD BUG\n\n1. Nằm ngửa, tay và gối nâng 90 độ\n2. Lưng dưới ép chặt xuống sàn\n3. Duỗi thẳng tay phải và chân trái\n4. Hạ xuống chậm, đổi bên\n5. Giữ hông và lưng ổn định\n6. Thở ra khi duỗi, hít vào khi thu\n\n💡 Luôn giữ lưng dính sàn!"
            
            "standard push-up" -> "💪 TẬP HÍT ĐẤT CHUẨN\n\n1. Chống tay rộng bằng vai\n2. Thân người thẳng từ đầu đến gót\n3. Hạ ngực xuống gần sàn\n4. Đẩy mạnh lên vị trí bắt đầu\n5. Siết cơ ngực và tay sau khi lên\n6. Giữ core căng, không võng lưng\n\n💡 Hít vào khi hạ, thở ra khi đẩy!"
            
            "wide push-up" -> "💪 TẬP HÍT ĐẤT TAY RỘNG\n\n1. Chống tay rộng hơn vai rõ rệt\n2. Thân người thẳng, core căng\n3. Hạ người chậm và có kiểm soát\n4. Đẩy lên mạnh từ cơ ngực\n5. Giữ khuỷu tay khoảng 45 độ\n6. Siết cơ ngực ở vị trí trên cùng\n\n💡 Tránh vai bị đau, giữ bả vai ổn định!"
            
            "diamond push-up" -> "💎 TẬP HÍT ĐẤT KIM CƯƠNG\n\n1. Chống tay tạo hình kim cương\n2. Các ngón tay chạm nhau dưới ngực\n3. Thân người thẳng, core căng\n4. Hạ người chậm, khuỷu tay sát thân\n5. Đẩy lên mạnh từ cơ tam bắp\n6. Giữ form đúng, không võng lưng\n\n💡 Nếu khó, thực hiện phiên bản quỳ gối!"
            
            "chair dip" -> "🪑 TẬP CHỐNG GHẾ\n\n1. Tay chống mép ghế cạnh hông\n2. Chân duỗi thẳng hoặc co gối\n3. Hạ người bằng khuỷu tay\n4. Giữ vai gần ghế, không nhô lên\n5. Đẩy lên mạnh từ cơ tam bắp\n6. Hạ sâu nhưng không quá gắng\n\n💡 Giữ ngực mở, vai kéo xuống!"
            
            "pike push-up" -> "⛰️ TẬP HÍT ĐẤT CHỮ V\n\n1. Tạo hình chữ V ngược\n2. Mông đẩy lên cao, đầu gối thẳng\n3. Hạ đỉnh đầu về gần sàn\n4. Đẩy lên mạnh từ cơ vai\n5. Giữ cổ trung lập, nhìn giữa tay\n6. Giữ core căng, không võng lưng\n\n💡 Tập trung vào cơ vai, không lưng!"
            
            "bulgarian split squat" -> "🇧🇬 TẬP SQUAT BULGARIA\n\n1. Đặt chân sau lên ghế\n2. Chân trước đứng rộng bằng hông\n3. Hạ người xuống thẳng đứng\n4. Gối trước 90 độ, không vượt mũi chân\n5. Đẩy lên mạnh bằng chân trước\n6. Giữ thăng bằng và thẳng lưng\n\n💡 Chọn khoảng cách chân phù hợp!"
            
            "wall sit" -> "🧱 TẬP NGỒI TỰA TƯỜNG\n\n1. Lưng tựa vào tường, chân rộng bằng vai\n2. Trượt xuống đến gối 90 độ\n3. Giữ lưng dính chặt vào tường\n4. Cẳng chân song song sàn\n5. Giữ nguyên vị trí, hít thở đều\n6. Đứng lên chậm khi hết thời gian\n\n💡 Dừng lại nếu đầu gối đau!"
            
            "donkey kick" -> "🐴 TẬP ĐÁ CHÂN SAU\n\n1. Chống tay gối, lưng thẳng\n2. Nâng một gối lên ngang hông\n3. Đá gót chân lên trên trần\n4. Siết chặt cơ mông ở đỉnh\n5. Hạ xuống chậm, có kiểm soát\n6. Đổi bên và lặp lại\n\n💡 Giữ core siết, không võng lưng!"
            
            "calf raise" -> "🦵 TẬP NHÓN BẮP CHÂN\n\n1. Đứng thẳng, chân rộng bằng hông\n2. Nhón gót chân lên cao nhất\n3. Giữ nguyên vị trí 1-2 giây\n4. Hạ gót xuống chậm, có kiểm soát\n5. Siết bắp chân ở vị trí trên cùng\n6. Lặp lại nhịp nhàng, không dùng đà\n\n💡 Giữ nhịp chậm để hiệu quả hơn!"
            
            else -> "📋 HƯỚNG DẪN TẬP $exerciseName\n\n1. Bắt đầu với tư thế thoải mái\n2. Thực hiện động tác một cách từ từ\n3. Tập trung vào cơ đang làm việc\n4. Hít thở đều và đúng nhịp\n5. Giữ form đúng trong suốt bài tập\n6. Nghỉ khi cần thiết\n\n💡 Lắng nghe cơ thể và tập an toàn!"
        }
    }

    private fun finishWorkout() {
        Snackbar.make(binding.root, "Hoan thanh bai tap! Tuyet voi! \uD83D\uDCAA", Snackbar.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        timer?.cancel()
        timer = null
        _binding = null
        super.onDestroyView()
    }
}