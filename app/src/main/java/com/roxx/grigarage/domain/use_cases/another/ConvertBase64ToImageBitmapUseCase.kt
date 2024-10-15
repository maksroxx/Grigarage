package com.roxx.grigarage.domain.use_cases.another

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayInputStream

class ConvertBase64ToImageBitmapUseCase {

    operator fun invoke(base64String: String): ImageBitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        // Преобразуем байты обратно в Bitmap
        val inputStream = ByteArrayInputStream(decodedBytes)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        // Преобразуем Bitmap в ImageBitmap для использования в Jetpack Compose
        return bitmap.asImageBitmap()
    }
}