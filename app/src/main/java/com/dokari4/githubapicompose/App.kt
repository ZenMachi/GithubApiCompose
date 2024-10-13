package com.dokari4.githubapicompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
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
import com.dokari4.githubapicompose.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

   val title = when (currentRoute) {
       Routes.HOME_SCREEN -> "Home"
       Routes.SEARCH_SCREEN -> "Search"
       Routes.SETTING_SCREEN -> "Setting"
       else -> ""
   }

    val listOfBottomNavItem = listOf(
        BottomNavItem(
            route = Routes.HOME_SCREEN,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            title = "Home"
        ),
        BottomNavItem(
            route = Routes.SEARCH_SCREEN,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            title = "Search"
        ),
        BottomNavItem(
            route = Routes.SETTING_SCREEN,
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            title = "Setting"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = title) })
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