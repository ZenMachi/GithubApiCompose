package com.dokari4.githubapicompose.ui.components

import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dokari4.githubapicompose.ui.navigation.Routes

@Composable
fun BottomNavigationBar(items: List<BottomNavItem>, navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            val isSelected = currentDestination
                ?.hierarchy
                ?.any { it.route == item.route::class.qualifiedName } == true

            val logRouteNow = currentDestination?.let {
                when (it.route) {
                    Routes.MainScreen.HomeScreen::class.qualifiedName -> "HomeScreen"
                    Routes.MainScreen.SearchScreen::class.qualifiedName -> "SearchScreen"
                    Routes.MainScreen.SettingScreen::class.qualifiedName -> "SettingScreen"
                    else -> "Other"
                }
            }
            NavigationBarItem(
                icon = {
                    BottomNavbarIcon(
                        isSelected = isSelected,
                        selectedIcon = item.selectedIcon,
                        unselectedIcon = item.unselectedIcon,
                        title = item.title
                    )
                },
                label = { Text(text = item.title) },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route)
                    Log.d("BottomNavigationBar", "BottomNavigationBar: $logRouteNow")
                }
            )
        }
    }
}

@Composable
private fun BottomNavbarIcon(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
) {
    Icon(
        imageVector = if (isSelected) selectedIcon else unselectedIcon,
        contentDescription = title
    )
}

data class BottomNavItem(
    val route: Any,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String
)

