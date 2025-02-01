package com.ritesh.mycaller.components

import android.content.res.Configuration
import android.provider.CalendarContract
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.R
import com.ritesh.mycaller.ui.theme.MyCallerTheme

@Composable
fun NavigationItem(
    title:String,
    icon:ImageVector,
    isSelected:Boolean,
    modifier: Modifier=Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color = if (isSelected) MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.tertiary)
            .padding(10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Calls",
            tint = if(isSelected) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.secondary
        )
        Text(
            text = title,
            color = if(isSelected) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true, )
@Composable
private fun NavigationItemPrev() {
    MyCallerTheme {
        NavigationItem(
            "Favorites", Icons.Default.Star, true)
    }
}