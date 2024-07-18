package com.pinheiro.marvelhqs.presenter.ui.comic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pinheiro.marvelhqs.presenter.ComicItem
import com.pinheiro.marvelhqs.presenter.MarvelViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComicScreen() {

    val viewModel: MarvelViewModel = koinViewModel()
    val state = viewModel.state
    val lazeListState = rememberLazyListState()

//    viewModel.getCharacters()
    LazyColumn(
        state = lazeListState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//
        items(state.items.size) { i ->
            val item = state.items[i]
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.getCharacters()
            }
            ComicItem(comic = item,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}