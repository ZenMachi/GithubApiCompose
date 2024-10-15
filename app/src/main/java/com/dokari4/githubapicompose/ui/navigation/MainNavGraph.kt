package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dokari4.githubapicompose.ui.home.HomeScreen
import com.dokari4.githubapicompose.ui.home.HomeViewModel
import com.dokari4.githubapicompose.ui.search.SearchScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.HomeScreen,
        modifier = modifier
    ) {
        composable<Routes.MainScreen.HomeScreen> {
            HomeScreen(
                viewModel = homeViewModel,
                onCardClick = { rootNavController.navigate(Routes.DetailScreen(it)) })
        }
        composable<Routes.MainScreen.SearchScreen> {
            SearchScreen()
        }
        composable<Routes.MainScreen.SettingScreen> {
            Text("This is Setting Screen")
        }
    }
}