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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ritesh.mycaller.R

@Composable
fun DialerBottomSheet(updatePhoneNumber:(String)->Unit,modifier: Modifier = Modifier) {
    var contact by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)) {
            Text(
                text = contact,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 32.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            )
            IconButton(onClick = {
                contact=contact.dropLast(1)
                updatePhoneNumber(contact)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_backspace_24),
                    contentDescription = "Backspace",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray,
        )
        Column {
            KeysRow(listOf("1","2","3"), onClick = {
                contact+=it
                updatePhoneNumber(contact)
            })
            KeysRow(listOf("4","5","6"), onClick = {
                contact+=it
                updatePhoneNumber(contact)
            })
            KeysRow(listOf("7","8","9"), onClick = {
                contact+=it
                updatePhoneNumber(contact)
            })
            KeysRow(listOf("*","0","#"), onClick = {
                contact+=it
                updatePhoneNumber(contact)
            })
        }
        ElevatedButton(onClick = {},
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF00FA9A)
            ),
            modifier = Modifier.padding(top = 6.dp, bottom = 16.dp)
            ) {
            Row(verticalAlignment = Alignment.CenterVertically
                ) {
                Icon(Icons.Default.Call,
                    contentDescription = "Call Icon",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
                )
                Text("Call",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                    )
            }
        }
    }
}