package com.ritesh.mycaller.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.R
import com.ritesh.mycaller.models.Contact

@Composable
fun ContactItem(contact: Contact,action:Int, onClick:()->Unit,modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier=modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(MaterialTheme.shapes.small)
        .background(
            color = if (expanded) MaterialTheme.colorScheme.tertiary else Color.Transparent
        )
        .padding(12.dp)
        .clickable { if(action!=1){expanded = !expanded}
            onClick()
        }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(contact.profilePicture==null) ProfilePlaceHolder()
            else Image(
                bitmap = contact.profilePicture.asImageBitmap(),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50))
            )
            Text(
                text = contact.name,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(animationSpec = tween(300)),
            exit = shrinkVertically(animationSpec = tween(300))
        ) {
            Column {
                HorizontalDivider(
                    thickness = 2.dp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Text(
                    text = contact.phoneNumber,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionIconButton("Call",
                        R.drawable.baseline_call_24,
                        onClick = {}
                    )
                    ActionIconButton("Text",
                        R.drawable.baseline_sms_24,
                        onClick = {}
                    )
                    ActionIconButton("Remove",
                        R.drawable.baseline_delete_24,
                        onClick = {}
                    )
                }
            }
        }
    }
}