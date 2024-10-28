package com.dokari4.githubapicompose.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    title: String,
    isHomeScreen: Boolean,
    onNavigateFavorites: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            if (isHomeScreen) {
                IconButton(
                    onClick = onNavigateFavorites
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }
            }
        }
    )
}