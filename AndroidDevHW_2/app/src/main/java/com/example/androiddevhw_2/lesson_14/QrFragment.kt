//package com.example.androiddevhw_2.lesson_14
//
//import android.annotation.SuppressLint
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.camera.core.*
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.lifecycle.Observer
//import com.example.androiddevhw_2.R
//import com.example.androiddevhw_2.databinding.QrFragmentBinding
//import com.google.mlkit.vision.barcode.Barcode
//import com.google.mlkit.vision.barcode.BarcodeScanner
//import com.google.mlkit.vision.barcode.BarcodeScannerOptions
//import com.google.mlkit.vision.barcode.BarcodeScanning
//import com.google.mlkit.vision.common.InputImage
//import java.lang.Exception
//import java.util.concurrent.Executors
//
//class QrFragment : Fragment() {
//
//    companion object {
//        fun newInstance() = QrFragment()
//    }
//
//    private lateinit var viewModel: QrViewModel
//    private lateinit var binding: QrFragmentBinding
//    private var cameraProvider: ProcessCameraProvider? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = QrFragmentBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(QrViewModel::class.java)
//       // viewModel.context = requireContext()
//        /*viewModel.processCameraProvider.observe(viewLifecycleOwner, { provider ->
//           cameraProvider = provider
//       })*/
//    }
//
//    override fun onResume() {
//        super.onResume()
//        setupCamera()
//    }
//
//    fun setupCamera() {
//        val lensFacing = CameraSelector.LENS_FACING_BACK
//        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
//        /*viewModel.processCameraProvider.observe(viewLifecycleOwner, { provider ->
//            cameraProvider = provider
//        })*/
//        scanForResult(cameraSelector)
//        bindPreview()
//    }
//
//    private fun bindPreview() {
//        val scanOptions = BarcodeScannerOptions.Builder()
//            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
//            .build()
//        val scanner = BarcodeScanning.getClient(scanOptions)
//
//        val analysisUseCase = ImageAnalysis.Builder()
//            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
//            .setTargetRotation(binding.preview.display.rotation)
//            .build()
//
//        val cameraExecutor = Executors.newSingleThreadExecutor()
//        analysisUseCase?.setAnalyzer(cameraExecutor, ImageAnalysis.Analyzer { imageProxy ->
//            processImageProxy(scanner, imageProxy)
//        })
//    }
//
//    @SuppressLint("UnsafeOptInUsageError")
//    private fun processImageProxy(scanner: BarcodeScanner, imageProxy: ImageProxy) {
//        val inputImage =
//            InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)
//        scanner.process(inputImage).addOnSuccessListener { barcodes ->
//            barcodes.forEach {
//                println("ACADEMY $it")
//            }
//        }
//            .addOnFailureListener {
//                Log.e("Camera", it.localizedMessage)
//            }
//            .addOnCompleteListener {
//                imageProxy.close()
//            }
//
//
//    }
//
//    private fun scanForResult(cameraSelector: CameraSelector) {
//        if (cameraProvider == null) {
//            return
//        }
//
//        val previewUseCase = Preview.Builder()
//            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
//            .setTargetRotation(binding.preview.display.rotation)
//            .build()
//        previewUseCase.setSurfaceProvider(binding.preview.createSurfaceProvider())
//
//        try {
//            cameraProvider?.bindToLifecycle(this, cameraSelector, previewUseCase)
//        } catch (e: Exception) {
//            Log.e("Camera", e.localizedMessage)
//        }
//    }
//
//}