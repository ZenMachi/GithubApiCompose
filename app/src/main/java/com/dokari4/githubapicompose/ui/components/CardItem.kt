package com.dokari4.githubapicompose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.data.remote.dto.UserDto

@Composable
fun CardItem(modifier: Modifier = Modifier, data: UserDto) {

    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier.height(72.dp),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                model = data.avatar,
                contentDescription = "Photo of ${data.idName}"
            )
            Spacer(Modifier.width(8.dp))
            Text(text = data.idName)
        }
    }
}