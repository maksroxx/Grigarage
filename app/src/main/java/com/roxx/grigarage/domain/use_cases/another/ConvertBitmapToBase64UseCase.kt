package com.roxx.grigarage.domain.use_cases.another

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class ConvertBitmapToBase64UseCase {

    operator fun invoke(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        // Сжатие Bitmap в формат JPEG с качеством 10%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        // Преобразование массива байтов в Base64 строку
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}