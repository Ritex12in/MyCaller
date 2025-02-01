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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.basics.ContactHelper
import com.ritesh.mycaller.basics.Utils
import com.ritesh.mycaller.components.AddContact
import com.ritesh.mycaller.components.AddContactBottomSheet
import com.ritesh.mycaller.components.ContactList
import com.ritesh.mycaller.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(viewModel: ContactViewModel,paddingValues: PaddingValues,modifier: Modifier = Modifier) {
    val contactMap by viewModel.contacts.collectAsState()
    val contacts = contactMap.values.toList()
    val hasPermission by viewModel.hasPermissionForRead.collectAsState()
    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.onPermissionGrantedForRead()
        }
    }

    LaunchedEffect(hasPermission) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    if (hasPermission) {

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ){
                AddContactBottomSheet(
                    name = name,
                    phoneNumber = phoneNumber,
                    onValueChangeName = {name=it},
                    onValueChangeNumber = {phoneNumber=it},
                    onClick = {
                        if(ContactHelper().addContact(context,name, phoneNumber)){
                            showBottomSheet = false
                            phoneNumber = ""
                            name = ""
                            Utils.showToast(context, "Contact added successfully")
                        }
                        else{
                            Utils.showToast(context, "Failed to add contact")
                        }
                    }
                )
            }
        }

        Column(modifier = modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(color = MaterialTheme.colorScheme.onSecondary)
        ) {
            AddContact(
                onClick = {
                    showBottomSheet=true
                }
            )
            ContactList(contacts,
                onClick = {Utils.showToast(context, "Clicked on contact")}
                )
        }
    }
    else{
        Text("Permission denied. Please grant Contact permission.")
    }
}