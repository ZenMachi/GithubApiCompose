package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dokari4.githubapicompose.ui.MainScreen
import com.dokari4.githubapicompose.ui.detail.DetailScreen
import com.dokari4.githubapicompose.ui.favorite.FavoriteScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen,
        modifier = modifier
    ) {
        composable<Routes.MainScreen> {
            MainScreen(
                rootNavController = navController,
                onNavigateFavorites = {
                    navController.navigate(Routes.FavoritesScreen)
                }
            )
        }

        composable<Routes.DetailScreen> {
            val args = it.toRoute<Routes.DetailScreen>()
            DetailScreen(
                username = args.username,
                /*
                *   navController.navigateUp() fixed problem when user
                *   rapidly clicking back button
                *   but this function only navigate to previous screen
                *
                *   popBackStack() if lifecycle is not checked will be always triggering
                *   then function till Screen goes blank
                * */
                onBackClick = { navController.popBackStack() },
                onNavigateToDetailScreen = { username ->
                    navController.navigate(Routes.DetailScreen(username))
                }
            )
        }
        composable<Routes.FavoritesScreen> {
            FavoriteScreen(
                onBackClick = { navController.popBackStack() },
                onCardClick = { username ->
                    navController.navigate(Routes.DetailScreen(username))
                }
            )
        }
    }
}