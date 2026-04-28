package com.nhom10.aifitnutrition.ui.food

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nhom10.aifitnutrition.R
import com.nhom10.aifitnutrition.data.model.FoodLog
import com.nhom10.aifitnutrition.databinding.FragmentCameraBinding
import com.nhom10.aifitnutrition.util.DateUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FoodCameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodViewModel by viewModels {
        FoodViewModel.Factory(requireActivity().application)
    }

    private var imageCapture: ImageCapture? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private lateinit var cameraExecutor: ExecutorService

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startCamera()
        else Snackbar.make(binding.root, getString(R.string.permission_camera), Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Handle back button press — release camera and navigate back
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    closeCamera()
                }
            }
        )

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }

        binding.btnCloseCamera.setOnClickListener { closeCamera() }
        binding.btnCapture.setOnClickListener { capturePhoto() }

        viewModel.analysisState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FoodAnalysisState.Loading -> binding.layoutAnalyzing.visibility = View.VISIBLE
                is FoodAnalysisState.Success -> {
                    binding.layoutAnalyzing.visibility = View.GONE
                    showAnalysisResult(state.result)
                }
                is FoodAnalysisState.Error -> {
                    binding.layoutAnalyzing.visibility = View.GONE
                    Snackbar.make(binding.root, "Analysis failed: ${state.message}", Snackbar.LENGTH_LONG).show()
                    viewModel.clearAnalysisState()
                }
                else -> binding.layoutAnalyzing.visibility = View.GONE
            }
        }
    }

    /** Properly releases camera resources then navigates back */
    private fun closeCamera() {
        try {
            cameraProvider?.unbindAll()
        } catch (e: Exception) {
            // ignore
        }
        viewModel.clearAnalysisState()
        if (findNavController().currentDestination?.id == R.id.foodCameraFragment) {
            findNavController().navigateUp()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()
            try {
                cameraProvider?.unbindAll()
                cameraProvider?.bindToLifecycle(
                    viewLifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Snackbar.make(binding.root, "Camera error: ${e.message}", Snackbar.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun capturePhoto() {
        val capture = imageCapture ?: return
        capture.takePicture(
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    val bitmap = image.toBitmap()
                    image.close()
                    viewModel.analyzeFoodImage(bitmap)
                }
                override fun onError(exception: ImageCaptureException) {
                    Snackbar.make(binding.root, "Capture failed: ${exception.message}", Snackbar.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun showAnalysisResult(result: com.nhom10.aifitnutrition.ai.FoodAnalysisResult) {
        val meals = arrayOf("Breakfast", "Lunch", "Dinner", "Snack")
        var selectedMeal = "breakfast"

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("✨ AI Analysis Complete")
            .setMessage(
                "${result.foodName}\n\n" +
                "🔥 Calories: ${result.calories} kcal\n" +
                "💪 Protein: ${result.proteinG}g\n" +
                "🍞 Carbs: ${result.carbsG}g\n" +
                "🥑 Fat: ${result.fatG}g\n" +
                "⚖️ Portion: ~${result.portionGrams}g\n\n" +
                if (result.healthNote.isNotEmpty()) "💡 ${result.healthNote}" else ""
            )
            .setSingleChoiceItems(meals, 0) { _, which ->
                selectedMeal = meals[which].lowercase()
            }
            .setPositiveButton("Log It! ✅") { _, _ ->
                viewModel.insertFoodLog(
                    FoodLog(
                        mealType = selectedMeal,
                        foodName = result.foodName,
                        calories = result.calories,
                        proteinG = result.proteinG,
                        carbsG = result.carbsG,
                        fatG = result.fatG,
                        portionGrams = result.portionGrams,
                        date = DateUtils.today(),
                        isAiAnalyzed = true
                    )
                )
                viewModel.clearAnalysisState()
                if (findNavController().currentDestination?.id == R.id.foodCameraFragment) {
                    findNavController().navigateUp()
                }
            }
            .setNegativeButton("Discard") { _, _ ->
                viewModel.clearAnalysisState()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            cameraProvider?.unbindAll()
        } catch (e: Exception) {
            // ignore
        }
        cameraExecutor.shutdown()
        _binding = null
    }
}
