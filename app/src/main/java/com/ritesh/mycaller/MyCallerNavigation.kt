package com.ritesh.mycaller

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ritesh.mycaller.constant.Routes
import com.ritesh.mycaller.screens.MainScreen
import com.ritesh.mycaller.screens.SearchContactScreen
import com.ritesh.mycaller.viewmodel.ContactViewModel

@Composable
fun MyCallerNavigation(application: Application) {
    val navController = rememberNavController()
    val contactViewModel = ContactViewModel(application)
    NavHost(navController = navController, startDestination = Routes.MAINSCREEN){
        composable(Routes.MAINSCREEN){
            MainScreen(navController, application)
        }

        composable(Routes.SEARCHSCREEN+"/{action}",
        arguments = listOf(navArgument("action"){type = NavType.IntType})
        ){ backStackEntry ->
            val action = backStackEntry.arguments?.getInt("action")?:0
            SearchContactScreen(action,navController, contactViewModel)
        }
    }
}