package com.dokari4.githubapicompose.ui.components.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dokari4.githubapicompose.ui.components.CardItem
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.ui.detail.DetailScreenState

@Composable
fun ListFollow(
    onFetchData: () -> Unit,
    state: DetailScreenState,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        onFetchData()
    }

    if (state.isLoadingFollowers) ShowProgressBar()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(state.listFollowers) { index, data ->
            val firstItem = if (index == 0) 16.dp else 0.dp
            CardItem(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = firstItem)
                    .fillMaxWidth()
                    .clickable { onNavigate(data.idName) },
                data = data
            )
        }
    }
}