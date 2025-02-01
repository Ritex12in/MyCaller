package com.ritesh.mycaller.components.keypad

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.R
import com.ritesh.mycaller.basics.Utils
import com.ritesh.mycaller.ui.theme.MyCallerTheme

@Composable
fun DialerBottomSheet(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)) {
            TextField(
                value = "",
                onValueChange = {  },
                placeholder = {  },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_backspace_24),
                    contentDescription = "Backspace")
            }
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(4.dp)
        )
        Column {
            KeysRow(listOf("1","2","3"), onClick = {Utils.showToast(context, "Clicked: $it")})
            KeysRow(listOf("4","5","6"), onClick = {Utils.showToast(context, "Clicked: $it")})
            KeysRow(listOf("7","8","9"), onClick = {Utils.showToast(context, "Clicked: $it")})
            KeysRow(listOf("*","0","#"), onClick = {Utils.showToast(context, "Clicked: $it")})
        }
        ElevatedButton(onClick = {},
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF00FA9A)
            ),
            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            ) {
            Row(verticalAlignment = Alignment.CenterVertically
                ) {
                Icon(Icons.Default.Call,
                    contentDescription = "Call Icon"
                )
                Text("Call"
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiallerPrev() {
    MyCallerTheme {
        DialerBottomSheet()
    }
}