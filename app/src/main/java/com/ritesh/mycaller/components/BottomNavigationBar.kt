package com.ritesh.mycaller.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.ui.theme.MyCallerTheme

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int, String) -> Unit){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
            .clip(MaterialTheme.shapes.large)
            .shadow(16.dp, MaterialTheme.shapes.large,)
            .background(color = MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth()
    ){
        NavigationItem(
            title = "Favorites",
            icon = Icons.Default.Star,
            isSelected = selectedTab==0,
            modifier = Modifier.padding(8.dp)
                .clickable { onTabSelected(0, "Favorites") }
        )
        NavigationItem(
            title = "Recents",
            icon = Icons.Default.Call,
            isSelected = selectedTab==1,
            modifier = Modifier.padding(8.dp)
                .clickable { onTabSelected(1,"Recents") }
        )
        NavigationItem(
            title = "Contacts",
            icon = Icons.Default.Person,
            isSelected = selectedTab==2,
            modifier = Modifier.padding(8.dp)
                .clickable { onTabSelected(2,"Contacts") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPrev(){
    MyCallerTheme {
        BottomNavigationBar(
            0,
            onTabSelected = {x,y->}
        )
    }
}