package ru.ikom.storekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val viewModel = (application as App).viewModel
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content(viewModel)
                }
            }
        }
    }
}


@Composable
fun Content(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    LazyColumn {
        items(uiState.goods, key = { item -> item.id }) {
            GoodItem(it)
        }
    }
}

@Composable
fun GoodItem(item: GoodUi) {
    Column(modifier = Modifier.fillMaxWidth().height(400.dp)) {
        Text(
            modifier = Modifier.padding(4.dp).fillMaxWidth().height(20.dp),
            text = item.title,
            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 20.sp)
        )

        Text(
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp).fillMaxWidth().height(20.dp),
            text = item.description,
            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 15.sp)
        )

        AsyncImage(
            modifier = Modifier.fillMaxWidth().weight(1f),
            model = item.images[0],
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}