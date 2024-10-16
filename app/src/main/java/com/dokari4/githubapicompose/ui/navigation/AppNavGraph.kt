package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dokari4.githubapicompose.MainScreen
import com.dokari4.githubapicompose.ui.detail.DetailScreen
import com.dokari4.githubapicompose.ui.detail.DetailViewModel
import com.dokari4.githubapicompose.ui.home.HomeViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen,
        modifier = modifier
    ) {

        composable<Routes.MainScreen> {
            MainScreen(viewModel = homeViewModel, rootNavController = navController)
        }

        composable<Routes.DetailScreen> {
            val args = it.toRoute<Routes.DetailScreen>()
            DetailScreen(
                username = args.username,
                viewModel = detailViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}