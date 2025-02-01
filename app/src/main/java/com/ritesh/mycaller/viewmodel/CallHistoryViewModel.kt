package com.ritesh.mycaller.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ritesh.mycaller.basics.CallHistory
import com.ritesh.mycaller.models.CallDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class CallHistoryViewModel(application: Application):AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _hasPermission = MutableStateFlow(false)
    val hasPermission: StateFlow<Boolean> get() = _hasPermission

    private val _callType = MutableStateFlow(0)
    val callType:StateFlow<Int> get() = _callType

    private val _callLogs = MutableStateFlow(emptyList<CallDetails>())
    val callLogs: StateFlow<List<CallDetails>> get() = _callLogs


    init {
        checkPermissionAndFetchData()
    }

    private fun checkPermissionAndFetchData() {
        viewModelScope.launch {
            val isGranted = context.checkSelfPermission(Manifest.permission.READ_CALL_LOG) ==
                    PackageManager.PERMISSION_GRANTED
            _hasPermission.value = isGranted

            if (isGranted) {
                fetchCallLogs()
            }
        }
    }

    fun onPermissionGranted() {
        _hasPermission.value = true
        fetchCallLogs()
    }

    private fun fetchCallLogs() {
        val callHistory = CallHistory(context)
        _callLogs.value = callHistory.fetchCallHistory()
    }

    fun setCallType(type:Int){
        _callType.value = type
    }
}