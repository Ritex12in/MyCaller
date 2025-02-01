package com.ritesh.mycaller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ritesh.mycaller.screens.MainScreen
import com.ritesh.mycaller.ui.theme.MyCallerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCallerTheme {
                MyCallerNavigation(this.application)
            }
        }
    }
}