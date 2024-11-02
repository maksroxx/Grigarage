package com.roxx.grigarage.di

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import com.roxx.grigarage.domain.use_cases.another.TakePhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CameraModule {

    @Provides
    @Singleton
    fun provideCameraController(@ApplicationContext context: Context): LifecycleCameraController {
        val cameraController = LifecycleCameraController(context)
        cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        cameraController.setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        return cameraController
    }

    @Provides
    @Singleton
    fun provideTakePhotoUseCase(
        cameraController: LifecycleCameraController,
        @ApplicationContext context: Context
    ): TakePhotoUseCase {
        return TakePhotoUseCase(cameraController, context)
    }
}