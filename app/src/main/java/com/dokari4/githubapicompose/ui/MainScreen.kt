package com.dokari4.githubapicompose.ui

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dokari4.githubapicompose.ui.components.common.BottomNavItem
import com.dokari4.githubapicompose.ui.components.common.CustomBottomBar
import com.dokari4.githubapicompose.ui.components.common.MainAppBar
import com.dokari4.githubapicompose.ui.navigation.MainNavGraph
import com.dokari4.githubapicompose.ui.navigation.Routes
import com.dokari4.githubapicompose.utils.ObserveAsEvents
import com.dokari4.githubapicompose.utils.SnackbarController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    rootNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    onNavigateFavorites: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    val title = currentDestination?.let {
        when (it.route) {
            Routes.MainScreen.HomeScreen::class.qualifiedName -> "Home"
            Routes.MainScreen.SearchScreen::class.qualifiedName -> "Search"
            Routes.MainScreen.SettingScreen::class.qualifiedName -> "Settings"
            else -> "Other"
        }
    } ?: "Other"
    val isHomeScreen = currentDestination?.hierarchy?.any {
        it.route == Routes.MainScreen.HomeScreen::class.qualifiedName
    } ?: false

    val listOfBottomNavItem = listOf(
        BottomNavItem(
            route = Routes.MainScreen.HomeScreen,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            title = "Home"
        ),
        BottomNavItem(
            route = Routes.MainScreen.SearchScreen,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            title = "Search"
        ),
        BottomNavItem(
            route = Routes.MainScreen.SettingScreen,
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            title = "Setting"
        )
    )

    ObserveAsEvents(
        flow = SnackbarController.events,
        key1 = snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            MainAppBar(
                title = title,
                isHomeScreen = isHomeScreen,
                onNavigateFavorites = onNavigateFavorites
            )
        },
        bottomBar = {
            CustomBottomBar(listOfBottomNavItem, navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavGraph(
                navController = navController,
                rootNavController = rootNavController,
            )
        }
    }
}