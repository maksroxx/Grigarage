package com.roxx.grigarage.domain.use_cases.another

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat

class TakePhotoUseCase(
    private val cameraController: LifecycleCameraController,
    private val context: Context
) {

    operator fun invoke(onPhotoTaken: (Bitmap) -> Unit, onError: (Exception) -> Unit) {
        cameraController.takePicture(
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    try {
                        val matrix = Matrix().apply {
                            postRotate(image.imageInfo.rotationDegrees.toFloat())
                        }
                        val bitmap = Bitmap.createBitmap(
                            image.toBitmap(),
                            0,
                            0,
                            image.width,
                            image.height,
                            matrix,
                            true
                        )
                        onPhotoTaken(bitmap)
                    } catch (e: Exception) {
                        onError(e)
                    } finally {
                        image.close()
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("TakePhotoUseCase", "Error capturing image", exception)
                    onError(exception)
                }
            })
    }
}