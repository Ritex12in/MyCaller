package com.ritesh.mycaller.screens

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ritesh.mycaller.R
import com.ritesh.mycaller.components.BottomNavigationBar
import com.ritesh.mycaller.components.TopBar
import com.ritesh.mycaller.constant.Routes
import com.ritesh.mycaller.viewmodel.CallHistoryViewModel
import com.ritesh.mycaller.viewmodel.ContactViewModel

@Composable
fun MainScreen(navController: NavController,application: Application){
    var selectedTab by rememberSaveable{ mutableIntStateOf( 1) }
    var tabText by rememberSaveable { mutableStateOf("Recents") }
    val callHistoryViewModel = CallHistoryViewModel(application)
    val contactViewModel = ContactViewModel(application)
    val action =0

    Scaffold(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        topBar = {
           TopBar(tabText,
               onSearchClick = {navController.navigate(Routes.SEARCHSCREEN+"/$action")}
               )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.tertiary,
                onClick = {
                    navController.navigate(Routes.SEARCHSCREEN+"/${action+2}")
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(70.dp)

            ) {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_dialpad_24),
                    contentDescription = "Dial pad",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        bottomBar = { BottomNavigationBar(selectedTab){p1,p2->
            selectedTab =p1
            tabText = p2
        } }
    ) {paddingValues->
        when(selectedTab){
            0-> FavoriteScreen(navController ,contactViewModel,paddingValues)
            1-> CallHistoryScreen(callHistoryViewModel,contactViewModel,paddingValues)
            2-> ContactScreen(contactViewModel,paddingValues)
        }
    }
}
