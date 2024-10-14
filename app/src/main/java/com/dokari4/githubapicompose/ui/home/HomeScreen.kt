package com.dokari4.githubapicompose.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.data.remote.response.UsersItem
import com.dokari4.githubapicompose.ui.components.CardItem
import com.dokari4.githubapicompose.ui.components.ShowProgressBar
import com.dokari4.githubapicompose.ui.components.TextSearchBar
import com.dokari4.githubapicompose.ui.theme.GithubApiComposeTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

val dummyData = UsersItem(
    id = 0,
    avatar = "https://avatars.githubusercontent.com/u/1?v=4",
    idName = "Test 1",
    url = "a"
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val users by viewModel.users.collectAsState()

    val context = LocalContext.current
    var text by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }

    Column {
        if (users.isLoading) ShowProgressBar()
        Log.d("Loading", users.isLoading.toString())
        if (users.errorMessage.isNotEmpty()) {
            ShowSnackBar(message = users.errorMessage)
        }

        LazyColumn(
            state = viewModel.listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(users.users) {
                CardItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    data = it
                )
            }

        }
    }
}

@Composable
fun ShowSnackBar(align: Alignment = Alignment.BottomCenter, message: String) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    SnackbarHost(hostState = snackbarHostState, modifier = Modifier)

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun CardItemPreview() {
    GithubApiComposeTheme {
        CardItem(data = dummyData)
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    GithubApiComposeTheme {
        val viewModel = HomeViewModel()
        HomeScreen(viewModel = viewModel)
    }
}