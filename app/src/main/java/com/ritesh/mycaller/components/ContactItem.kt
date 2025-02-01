package com.ritesh.mycaller.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.models.Contact

@Composable
fun ContactItem(contact: Contact,modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(12.dp)
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
}