package com.dokari4.githubapicompose.ui.home

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dokari4.githubapicompose.R
import com.dokari4.githubapicompose.ui.components.BottomNavigationBar
import com.dokari4.githubapicompose.ui.navigation.NavigationDestination
import com.dokari4.githubapicompose.ui.theme.GithubApiComposeTheme

object HomeDestination: NavigationDestination {
    override val id = R.string.app_name
    override val route = "home"
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }
    val navController = rememberNavController()
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(36.dp),
        trailingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) }
    )


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubApiComposeTheme {
        Greeting("Android")
    }
}