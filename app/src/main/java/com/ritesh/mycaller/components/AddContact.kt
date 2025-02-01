package com.ritesh.mycaller.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.R

@Composable
fun AddContact(onClick:()->Unit,modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(15.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_person_add_alt_24),
            contentDescription = "add person",
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text("Create new contact",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}