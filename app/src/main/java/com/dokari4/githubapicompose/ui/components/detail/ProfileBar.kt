package com.dokari4.githubapicompose.ui.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dokari4.githubapicompose.utils.formatNumber

@Composable
fun ProfileBar(
    height: Dp = 128.dp,
    photoUrl: String,
    name: String?,
    repoCount: Long,
    followerCount: Long,
    followingCount: Long
) {
    Row(
        modifier = Modifier
            .height(height)
            .padding(horizontal = 16.dp)
    ) {
        val placeholder = rememberVectorPainter(Icons.Default.Person)

        AsyncImage(
            modifier = Modifier
                .height(height)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Fit,
            placeholder = placeholder,
            model = photoUrl,
            contentDescription = "Photo of $name"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (name != null) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatsItem(
                    title = "Repo",
                    count = formatNumber(repoCount)
                )
                StatsItem(
                    title = "Followers",
                    count = formatNumber(followerCount)
                )
                StatsItem(
                    title = "Following",
                    count = formatNumber(followingCount)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileBarPreview() {
    ProfileBar(
        photoUrl = "",
        name = "Dokari4",
        repoCount = 10,
        followerCount = 100,
        followingCount = 1000
    )
}