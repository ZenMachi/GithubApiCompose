package com.dokari4.githubapicompose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val route: String, val selectedIcon: ImageVector) {
    data object Home : BottomNavItem("Home", "home", Icons.Default.Home)
    data object Search : BottomNavItem("Search", "search", Icons.Default.Search)
    data object Setting : BottomNavItem("Setting", "setting", Icons.Default.Settings)

}