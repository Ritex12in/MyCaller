package com.ritesh.mycaller.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ritesh.mycaller.components.CallHistoryItem
import com.ritesh.mycaller.components.CallTypeSelector
import com.ritesh.mycaller.viewmodel.CallHistoryViewModel
import com.ritesh.mycaller.viewmodel.ContactViewModel

@Composable
fun CallHistoryScreen(viewModel: CallHistoryViewModel,
                      contactViewModel: ContactViewModel,
                      paddingValues: PaddingValues,
                      modifier: Modifier = Modifier) {

    val hasPermission by viewModel.hasPermission.collectAsState()
    val itemList by viewModel.callLogs.collectAsState()
    val callType by viewModel.callType.collectAsState()
    val filteredList = if(callType==0) itemList
        else itemList.filter { callDetails -> callDetails.type==callType }
    val contactMap by contactViewModel.contacts.collectAsState()
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.onPermissionGranted()
        }
    }

    LaunchedEffect(hasPermission) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.READ_CALL_LOG)
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(color = MaterialTheme.colorScheme.onSecondary)
    ){
        if (hasPermission) {
            CallTypeSelector(callType,{
                viewModel.setCallType(it)
            })
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 3.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(color = MaterialTheme.colorScheme.onTertiary)
            ) {
                items(filteredList){item->
                    val contact = contactMap[item.number]
                    CallHistoryItem(item, contact)
                }
            }
        } else {
            Text("Permission denied. Please grant Call Log permission.")
        }
    }
}