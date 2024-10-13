package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dokari4.githubapicompose.ui.components.BottomNavItem
import com.dokari4.githubapicompose.ui.home.HomeDestination
import com.dokari4.githubapicompose.ui.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavItem.Search.route) {
            Text("This is Search Screen")
        }
        composable(route = BottomNavItem.Setting.route) {
            Text("This is Setting Screen")
        }
    }
}