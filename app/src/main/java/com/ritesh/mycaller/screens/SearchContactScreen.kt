package com.ritesh.mycaller.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ritesh.mycaller.R
import com.ritesh.mycaller.components.ContactList
import com.ritesh.mycaller.components.SearchTopAppBar
import com.ritesh.mycaller.components.keypad.DialerBottomSheet
import com.ritesh.mycaller.models.Contact
import com.ritesh.mycaller.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContactScreen(
    action:Int,
    navController: NavController,
    viewModel: ContactViewModel,
    modifier: Modifier = Modifier) {
    var isSearching by remember { mutableStateOf(action==0) }
    var searchQuery by remember { mutableStateOf("") }
    var showDialer by remember { mutableStateOf(action==2) }
    val sheetState = rememberModalBottomSheetState()
    val contactMap by viewModel.contacts.collectAsState()
    var contacts by remember { mutableStateOf(listOf<Contact>()) }
    contacts = contactMap.values.toList()
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    val hasPermission by viewModel.hasPermissionForRead.collectAsState()
    val context = LocalContext.current

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
    Scaffold(
        topBar = { SearchTopAppBar(
            title = if(action==1) "Choose a contact" else "Contacts",
            isSearching, searchQuery,
            onSearchQueryChange = { searchQuery = it },
            onToggleSearch = {
                searchQuery = ""
                isSearching = !isSearching },
            onBackClick = {navController.popBackStack()}
        )  },
        floatingActionButton = {
            if(action==2){
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        showDialer = true
                    },
                    modifier = Modifier
                        .padding(bottom = 36.dp)
                        .size(70.dp)

                ) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_dialpad_24),
                        contentDescription = "Dial pad",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.onSecondary
    ) { paddingValues ->
        if(showDialer){
            ModalBottomSheet(
                onDismissRequest = {showDialer=false},
                sheetState = sheetState,
                dragHandle = null,
                shape = RectangleShape
            ) {
                DialerBottomSheet(
                    updatePhoneNumber = {phoneNumber=it
                    searchQuery = it
                    }
                )
            }
        }
        ContactList(contacts.filter {
            it.name.contains(searchQuery, ignoreCase = true) || it.phoneNumber.contains(searchQuery, ignoreCase = true)
        }, modifier = Modifier.padding(paddingValues),
            action = action,
            onClick = {
                if (action==1) {
                    viewModel.addToFavorite(context, it.id)
                    navController.popBackStack()
                }
            }
        )
    }
}