package com.roxx.grigarage.presentation.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long, pattern: String = "dd MMM yyyy", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(pattern, locale)
    return formatter.format(Date(timestamp))
}

fun parseDateToTimestamp(dateString: String, pattern: String = "dd MMM yyyy", locale: Locale = Locale.getDefault()): Long {
    return try {
        val formatter = SimpleDateFormat(pattern, locale)
        val date = formatter.parse(dateString)
        date.time
    } catch (e: ParseException) {
        e.printStackTrace()
        0
    }
}
