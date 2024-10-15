package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.dokari4.githubapicompose.MainScreen
import com.dokari4.githubapicompose.ui.detail.DetailScreen
import com.dokari4.githubapicompose.ui.home.HomeScreen
import com.dokari4.githubapicompose.ui.home.HomeViewModel
import com.dokari4.githubapicompose.ui.search.SearchScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.route,
        modifier = modifier
    ) {

        composable(route = Routes.MainScreen.route) {
            MainScreen(viewModel = viewModel, rootNavController = navController)
        }

        composable(route = Routes.DetailScreen.route) {
            DetailScreen()
        }
    }
}