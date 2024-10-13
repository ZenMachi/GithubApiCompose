package com.dokari4.githubapicompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dokari4.githubapicompose.ui.components.BottomNavItem
import com.dokari4.githubapicompose.ui.components.BottomNavigationBar
import com.dokari4.githubapicompose.ui.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val listOfBottomNavItem = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Setting
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = currentRoute ?: "") })
        },
        bottomBar = {
            BottomNavigationBar(listOfBottomNavItem, navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AppNavHost(navController = navController)
        }
    }
}