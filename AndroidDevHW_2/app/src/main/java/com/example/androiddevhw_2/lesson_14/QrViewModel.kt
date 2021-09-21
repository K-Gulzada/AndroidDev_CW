package com.example.androiddevhw_2.lesson_14

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception
import java.util.concurrent.ExecutionException

class QrViewModel(application:Application) : AndroidViewModel(application) {

    private var cameraProviderLiveData: MutableLiveData<ProcessCameraProvider>? = null

    val processCameraProvider: LiveData<ProcessCameraProvider>
        get() {
            if (cameraProviderLiveData == null) {
                cameraProviderLiveData = MutableLiveData()
                val cameraProviderFuture = ProcessCameraProvider.getInstance(getApplication())
                cameraProviderFuture.addListener(
                    Runnable {
                        try {
                            cameraProviderLiveData!!.value = cameraProviderFuture.get()
                        } catch (e: ExecutionException) {
                            Log.e("Camera Exception", "Problem with Camera provider", e)
                        } catch (e: Exception) {
                            Log.e("Camera Exception", "Problem with Camera", e)
                        }
                    },
                    ContextCompat.getMainExecutor(getApplication())
                )
            }
            return cameraProviderLiveData!!
        }

   /* companion object{

    }*/
}