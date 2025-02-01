package com.ritesh.mycaller.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ritesh.mycaller.basics.Utils
import com.ritesh.mycaller.components.AddFavorite
import com.ritesh.mycaller.components.ContactList
import com.ritesh.mycaller.constant.Routes
import com.ritesh.mycaller.viewmodel.ContactViewModel

@Composable
fun FavoriteScreen(navController: NavController,viewModel: ContactViewModel,paddingValues: PaddingValues,modifier: Modifier = Modifier) {
    val contactMap by viewModel.contacts.collectAsState()
    val contacts = contactMap.filter { (_,contact)->
        contact.starred == 1
    }.values.toList()
    val hasPermissionForRead by viewModel.hasPermissionForRead.collectAsState()
    val hasPermissionForWrite by viewModel.hasPermissionForWrite.collectAsState()
    val context = LocalContext.current
    val action = 1


    val permissionLauncherForRead = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.onPermissionGrantedForRead()
        }
    }

    val permissionLauncherForWrite = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.onPermissionGrantedForWrite()
        }
    }

    LaunchedEffect(hasPermissionForRead) {
        if (!hasPermissionForRead) {
            permissionLauncherForRead.launch(Manifest.permission.READ_CONTACTS)
        }
        if (!hasPermissionForWrite){
            permissionLauncherForWrite.launch(Manifest.permission.WRITE_CONTACTS)
        }
    }

    if (hasPermissionForRead) {
        Column(modifier = modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(color = MaterialTheme.colorScheme.onSecondary)
        ) {
            AddFavorite(onClick = {
                if (hasPermissionForWrite) {
                    navController.navigate(Routes.SEARCHSCREEN+"/$action")
                }
                else{
                    Utils.showToast(context, "No permission for write contacts")
                }
            })
            ContactList(contacts,
                onClick = {Utils.showToast(context, "Clicked on favorite contact")}
            )
        }
    }
    else{
        Text("Permission denied. Please grant Contact permission.")
    }
}