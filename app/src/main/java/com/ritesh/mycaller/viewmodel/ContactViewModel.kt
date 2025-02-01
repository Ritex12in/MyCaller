package com.ritesh.mycaller.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ritesh.mycaller.basics.ContactHelper
import com.ritesh.mycaller.basics.Utils
import com.ritesh.mycaller.models.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    private val _hasPermissionForRead = MutableStateFlow(false)
    val hasPermissionForRead: StateFlow<Boolean> get() = _hasPermissionForRead

    private val _hasPermissionForWrite = MutableStateFlow(false)
    val hasPermissionForWrite: StateFlow<Boolean> get() = _hasPermissionForWrite

    private val _contacts = MutableStateFlow(emptyMap<String,Contact>())
    val contacts: StateFlow<Map<String, Contact>> get() = _contacts

    init {
        checkPermissionAndFetchData()
    }

    private fun checkPermissionAndFetchData() {
        viewModelScope.launch {
            val isGranted = context.checkSelfPermission(Manifest.permission.READ_CONTACTS) ==
                    PackageManager.PERMISSION_GRANTED
            _hasPermissionForRead.value = isGranted

            if (isGranted) {
                fetchContactList()
            }
        }
    }

    fun onPermissionGrantedForRead() {
        _hasPermissionForRead.value = true
        fetchContactList()
    }

    fun onPermissionGrantedForWrite(){
        _hasPermissionForWrite.value = true
    }

    private fun fetchContactList(){
        _contacts.value = ContactHelper().loadContacts(context)
    }

    fun addToFavorite(context: Context, contactId:String){
        if (ContactHelper().markContactAsFavorite(context,contactId,true)) {
            Utils.showToast(context, "Added to favorite")
        }
        else{
            Utils.showToast(context, "Failed to add")
        }
    }
    fun removeFromFavorite(context: Context, contactId:String){
        if(ContactHelper().markContactAsFavorite(context,contactId,false)){
            Utils.showToast(context, "Removed Successfully")
        }
        else{
            Utils.showToast(context, "Failed to remove")
        }
    }
}