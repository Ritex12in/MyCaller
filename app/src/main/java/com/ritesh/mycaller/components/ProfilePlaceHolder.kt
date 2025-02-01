package com.ritesh.mycaller.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.R

@Composable
fun ProfilePlaceHolder(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.baseline_person_24),
        contentDescription = "person",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(50))
            .background(color = MaterialTheme.colorScheme.surfaceTint)
    )
}