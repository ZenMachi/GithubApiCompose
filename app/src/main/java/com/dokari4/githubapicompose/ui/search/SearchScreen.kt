package com.dokari4.githubapicompose.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dokari4.githubapicompose.ui.components.TextSearchBar

/*
TODO 01: Create SearchScreen
TODO 02: Create SearchViewModel
TODO 03: Create UiState
TODO 04: Add Network Call in Api
*/

@Composable
fun SearchScreen() {
    Column {
        TextSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onButtonClick = {})

    }
}