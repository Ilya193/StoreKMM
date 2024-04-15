package ru.ikom.storekmm.android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun NetworkScreen(viewModel: NetworkViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    if (uiState.isLoading)
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    else if (uiState.isError)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.error))
            Button(onClick = {
                viewModel.fetchData()
            }) {
                Text(text = stringResource(R.string.retry))
            }
        }
    else {
        LazyColumn(modifier = Modifier.systemBarsPadding()) {
            items(uiState.goods, key = { item -> item.id }) {
                GoodItem(it)
            }
        }
    }
}

@Composable
fun GoodItem(item: GoodUi) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(20.dp),
            text = item.title,
            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 20.sp)
        )

        Text(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 4.dp)
                .fillMaxWidth()
                .height(20.dp),
            text = item.description,
            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 15.sp)
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            model = item.images[0],
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}