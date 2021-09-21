package com.example.androiddevhw_2.lesson_14

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevhw_2.R
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.Math.min
import java.util.concurrent.Executors
import kotlin.math.abs
import kotlin.math.max

class CameraActivity : AppCompatActivity() {

    private var previewView: PreviewView? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var cameraSelector: CameraSelector? = null
    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var previewUseCase: Preview? = null
    private var analysisUseCase: ImageAnalysis? = null

    private val screenAspectRatio: Int
        get() {
            val metrics = DisplayMetrics().also { previewView?.display?.getRealMetrics(it) }
            return aspectRatio(metrics.widthPixels, metrics.heightPixels)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        setupCamera()
    }

    private fun setupCamera() {
        previewView = findViewById(R.id.preview_view)
        cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

        ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(QrViewModel::class.java)
            .processCameraProvider
            .observe(
                this,
                Observer { provider: ProcessCameraProvider? ->
                    cameraProvider = provider
                    if (isCameraPermissionGranted()) {
                        bindCameraUseCases()
                    }
                }
            )

    }

    private fun bindCameraUseCases() {
        bindPreviewUseCase()
        bindAnalyseUseCase()
    }

    private fun bindPreviewUseCase() {
        if (cameraProvider == null) {
            return
        }
        if (previewUseCase != null) {
            cameraProvider!!.unbind(previewUseCase)
        }

        previewUseCase = Preview.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(previewView!!.display.rotation)
            .build()
        previewUseCase!!.setSurfaceProvider(previewView!!.createSurfaceProvider())

        try {
            cameraProvider!!.bindToLifecycle(
                this,
                cameraSelector!!,
                previewUseCase
            )
        } catch (illegalStateException: IllegalStateException) {
            illegalStateException.message?.let { Log.e(TAG, it) }
        } catch (illegalArgumentException: IllegalArgumentException) {
            illegalArgumentException.message?.let { Log.e(TAG, it) }
        }

    }

    private fun bindAnalyseUseCase() {
        val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient()

        if (cameraProvider == null) {
            return
        }
        if(analysisUseCase!=null){
            cameraProvider!!.unbind(analysisUseCase)
        }

        analysisUseCase = ImageAnalysis.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .setTargetRotation(previewView!!.display.rotation)
            .build()

        val cameraExecutor = Executors.newSingleThreadExecutor()
        analysisUseCase?.setAnalyzer(cameraExecutor, ImageAnalysis.Analyzer { imageProxy ->
            processImageProxy(barcodeScanner, imageProxy)
        })

    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun processImageProxy(scanner: BarcodeScanner, imageProxy: ImageProxy) {
        val inputImage =
            InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)
        scanner.process(inputImage).addOnSuccessListener { barcodes ->
            barcodes.forEach {
                Log.d(TAG, it.rawValue)
            }
        }
            .addOnFailureListener {
                Log.e("Camera", it.localizedMessage)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }


    }

    private fun aspectRatio(width:Int, height:Int):Int{
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if(abs(previewRatio-RATIO_4_3_VALUE)<= abs(previewRatio- RATIO_16_9_VALUE)){
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9

    }

     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<out String>,
         grantResults: IntArray
     ) {
         if (requestCode == PERMISSION_REQUEST) {
             if (isCameraPermissionGranted()) {

             } else {
                 Toast.makeText(this, "Camera Access is needed to continue", Toast.LENGTH_LONG)
                     .show()
             }
         }
         super.onRequestPermissionsResult(requestCode, permissions, grantResults)
     }

/*    private fun startCamera() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<QrFragment>(R.id.container)
        }
    }*/

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            baseContext,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
/*
    private fun checkCamera() {
        if (isCameraPermissionGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST
            )
        }
    }*/

    companion object {
        private const val PERMISSION_REQUEST = 1
        private val TAG = CameraActivity::class.java.simpleName
        private const val RATIO_4_3_VALUE = 4.0/3.0
        private const val RATIO_16_9_VALUE = 16.0/9.0
    }


}