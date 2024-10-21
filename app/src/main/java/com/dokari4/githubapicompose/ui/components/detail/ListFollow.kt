package com.dokari4.githubapicompose.ui.components.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import com.dokari4.githubapicompose.ui.components.CardItem
import com.dokari4.githubapicompose.ui.components.ShowProgressBar

@Composable
fun ListFollow(
    listUser: List<UserDto>,
    isLoading: Boolean,
    onNavigate: (String) -> Unit
) {
    /**
     * Calling LaunchedEffect from this composable make function always executed
     * Because this happen when composable recomposed / is it removed from view tree
     *
     * Solution are calling LaunchedEffect from parent composable
     */
//    LaunchedEffect(lifecycleOwner.lifecycle) {
//        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//            onFetchData()
//        }
//    }

    if (isLoading) ShowProgressBar()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(listUser) { index, data ->
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