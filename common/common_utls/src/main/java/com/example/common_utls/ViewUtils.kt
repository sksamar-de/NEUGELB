package com.example.common_utls

import android.os.Build
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatLongToUsd(amount: Long?): String {
    return try {
        if (amount == null) return "$0.00"
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        formatter.format(amount)
    } catch (e: Exception) {
        "$0.00"
    }
}

fun formatMinutesToDuration(totalMinutes: Long?): String {
    return try {
        if (totalMinutes == null || totalMinutes < 0) {
            return "0 minutes"
        }
        val hours = TimeUnit.MINUTES.toHours(totalMinutes)
        val minutes = totalMinutes % 60
        val hourString = if (hours > 0) {
            "$hours ${if (hours == 1L) "hour" else "hours"}"
        } else ""
        val minuteString = if (minutes > 0) {
            "$minutes ${if (minutes == 1L) "minute" else "minutes"}"
        } else ""
        val result = "$hourString $minuteString".trim()
        result.ifEmpty { "0 minutes" }
    } catch (e: Exception) {
        "0 minutes"
    }
}

fun formatDateWithSuffix(dateString: String?): String {
    return try {
        if (dateString.isNullOrEmpty()) return ""
        val day: Int
        val formattedRest: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = LocalDate.parse(dateString)
            day = date.dayOfMonth
            formattedRest = date.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.US))
        } else {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.US)
            val date = inputFormat.parse(dateString) ?: return dateString

            val calendar = java.util.Calendar.getInstance()
            calendar.time = date
            day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
            formattedRest = outputFormat.format(date)
        }
        val suffix = when {
            day in 11..13 -> "th"
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th"
        }
        "$day$suffix $formattedRest"
    } catch (e: Exception) {
        dateString ?: ""
    }
}