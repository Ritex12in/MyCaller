package com.ritesh.mycaller.models

data class CallDetails(
    val number: String,
    val date: Long,
    val duration: Long,
    val type: Int
)
