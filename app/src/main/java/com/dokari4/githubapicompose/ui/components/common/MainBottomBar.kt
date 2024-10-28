package com.dokari4.githubapicompose.ui.components.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CustomBottomBar(items: List<BottomNavItem>, navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            val isSelected = currentDestination
                ?.hierarchy
                ?.any { it.route == item.route::class.qualifiedName } == true

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
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
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

