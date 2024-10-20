package com.dokari4.githubapicompose.ui.components.lottie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dokari4.githubapicompose.R

@Composable
fun ErrorLottie(isPlaying: Boolean = true) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
        resId = R.raw.error_lottie)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations =  if (isPlaying) LottieConstants.IterateForever else 0,
    )
    LottieAnimation(
        composition = composition,
        progress = {
            progress
        }
    )
}