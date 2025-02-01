package com.ritesh.mycaller.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.ui.theme.MyCallerTheme

@Composable
fun CallTypeSelector(selectedTab:Int,callTypeSelected:(Int)->Unit,modifier: Modifier = Modifier) {
    val colorSelected = MaterialTheme.colorScheme.secondary
    val colorNonSelected = MaterialTheme.colorScheme.onPrimary
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Text("All",
            color = if(selectedTab==0) colorSelected else colorNonSelected,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                callTypeSelected(0)
                }
        )
        Text("Incoming",
            color = if(selectedTab==1) colorSelected else colorNonSelected,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    callTypeSelected(1)
                }
            )
        Text("Outgoing",
            color = if(selectedTab==2) colorSelected else colorNonSelected,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    callTypeSelected(2)
                }
            )
        Text("Missed",
            color = if(selectedTab==3) colorSelected else colorNonSelected,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    callTypeSelected(3)
                }
            )
    }
}

@Preview(showBackground = true)
@Composable
private fun CallTypeSelectorPrev() {
    MyCallerTheme {
        CallTypeSelector(2,{})
    }
}