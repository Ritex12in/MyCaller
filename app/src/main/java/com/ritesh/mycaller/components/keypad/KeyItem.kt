package com.ritesh.mycaller.components.keypad

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyItem(keyValue:String, onClick:()->Unit) {
    ElevatedButton(onClick = {onClick()},
        modifier = Modifier.size(68.dp)
        ) {
        Text(keyValue,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 42.sp
        )
    }
}