package com.ritesh.mycaller.models

import android.graphics.Bitmap

data class Contact(
    val id:String,
    val name: String,
    val phoneNumber: String,
    val profilePicture: Bitmap? = null,
    val starred:Int
)
