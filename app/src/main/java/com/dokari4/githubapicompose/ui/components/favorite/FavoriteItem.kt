package com.dokari4.githubapicompose.ui.components.favorite

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dokari4.githubapicompose.data.local.model.FavoriteEntity
import com.dokari4.githubapicompose.ui.components.common.CardItem
import kotlinx.coroutines.delay

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    item: FavoriteEntity,
    animationDuration: Int = 500,
    onSwipeToRemove: (FavoriteEntity) -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        },
        positionalThreshold = { it * .25f }
    )

    LaunchedEffect(isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onSwipeToRemove(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            modifier = modifier,
            state = dismissState,
            enableDismissFromStartToEnd = false,
            backgroundContent = { DismissibleBackground(dismissState) }
        ) {
            CardItem(
                username = item.username,
                avatarUrl = item.avatarUrl
            )
        }
    }
}