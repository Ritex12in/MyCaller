package com.ritesh.mycaller.basics

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Utils {
    fun formatDynamicDate(dateLong: Long): Pair<String, String> {
        val now = Calendar.getInstance()
        val inputDate = Calendar.getInstance().apply { timeInMillis = dateLong }

        val dateFormatWithYear = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val dateFormatWithoutYear = SimpleDateFormat("dd MMM", Locale.getDefault())
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault()) // 12-hour format with AM/PM

        val formattedDate = if (now.get(Calendar.YEAR) == inputDate.get(Calendar.YEAR)) {
            dateFormatWithoutYear.format(inputDate.time) // e.g., "24 Jan"
        } else {
            dateFormatWithYear.format(inputDate.time) // e.g., "24 Jan 2023"
        }

        val formattedTime = timeFormat.format(inputDate.time) // e.g., "02:30 PM"

        return Pair(formattedDate, formattedTime)
    }

    fun formatCallDurationVerbose(duration: Long): String {
        val hours = duration / 3600
        val minutes = (duration % 3600) / 60
        val seconds = duration % 60

        return buildString {
            if (hours > 0) append("$hours hr ")
            if (minutes > 0) append("$minutes min ")
            if (seconds > 0 || isEmpty()) append("$seconds sec")
        }.trim()
    }


    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}