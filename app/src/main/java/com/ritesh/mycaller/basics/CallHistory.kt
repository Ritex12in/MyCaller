package com.ritesh.mycaller.basics

import android.content.Context
import android.provider.CallLog
import com.ritesh.mycaller.models.CallDetails

class CallHistory(private val context: Context) {
    fun fetchCallHistory(): List<CallDetails> {
        val callDetailsList = mutableListOf<CallDetails>()
        val projection = arrayOf(
            CallLog.Calls.NUMBER,
            CallLog.Calls.DATE,
            CallLog.Calls.DURATION,
            CallLog.Calls.TYPE,
        )

        val cursor = context.contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            projection,
            null,
            null,
            "${CallLog.Calls.DATE} DESC"
        )

        cursor?.use {
            while (it.moveToNext()) {
                val number = it.getString(it.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                val date = it.getLong(it.getColumnIndexOrThrow(CallLog.Calls.DATE))
                val duration = it.getLong(it.getColumnIndexOrThrow(CallLog.Calls.DURATION))
                val type = it.getInt(it.getColumnIndexOrThrow(CallLog.Calls.TYPE))

                callDetailsList.add(
                    CallDetails(
                        number = number,
                        date = date,
                        duration = duration,
                        type = when (type) {
                            CallLog.Calls.INCOMING_TYPE -> 1
                            CallLog.Calls.OUTGOING_TYPE -> 2
                            CallLog.Calls.MISSED_TYPE -> 3
                            else -> 6
                        }
                    )
                )
            }
        }
        return callDetailsList
    }
}