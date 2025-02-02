package com.ritesh.mycaller.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.models.Contact

@Composable
fun ContactList(contactList:List<Contact>,action:Int,onClick:(Contact)->Unit,modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 3.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(color = MaterialTheme.colorScheme.onTertiary)
    ) {
        items(contactList){item->
            ContactItem(item,
                action,
                {onClick(item)}
            )
        }
    }
}