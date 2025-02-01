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
fun AddFavorite(modifier: Modifier = Modifier, onClick:()->Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(15.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_star_border_24),
            contentDescription = "add favorite",
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Text("Add new favorite",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}