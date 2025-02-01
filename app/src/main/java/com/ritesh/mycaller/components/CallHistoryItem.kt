package com.ritesh.mycaller.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.R
import com.ritesh.mycaller.basics.Utils
import com.ritesh.mycaller.models.CallDetails
import com.ritesh.mycaller.models.Contact

@Composable
fun CallHistoryItem(callDetails: CallDetails, contact: Contact?, modifier: Modifier = Modifier) {
    val date = Utils.formatDynamicDate(callDetails.date)
    val tintColor = if(callDetails.type==3) Color.Red else Color.Green
    var expanded by rememberSaveable { mutableStateOf(false) }
    val imageVector = when(callDetails.type){
        2-> ImageVector.vectorResource(R.drawable.baseline_call_made_24)
        3-> ImageVector.vectorResource(R.drawable.baseline_call_missed_24)
        else -> ImageVector.vectorResource(R.drawable.baseline_call_received_24)
    }
    Column(modifier=modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(MaterialTheme.shapes.small)
        .background(
            color = if (expanded) MaterialTheme.colorScheme.tertiary else Color.Transparent
        )
        .padding(12.dp)
        .clickable { expanded = !expanded }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (contact != null) {
                if (contact.profilePicture == null) ProfilePlaceHolder()
                else Image(
                    bitmap = contact.profilePicture.asImageBitmap(),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(50))
                )
            } else ProfilePlaceHolder()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = contact?.name ?: callDetails.number,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "received call",
                        tint = tintColor
                    )
                    Text(
                        date.first+", "+date.second,
                        color = if (callDetails.type == 3) Color.Red
                        else MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "call icon",
                tint = MaterialTheme.colorScheme.secondary
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(Utils.formatCallDurationVerbose(callDetails.duration),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                        Text(callDetails.number,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}