package com.pinheiro.marvelhqs.presenter.ui.comic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pinheiro.marvelhqs.presenter.MarvelViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComicScreen() {

    val viewModel: MarvelViewModel = koinViewModel()
    val state = viewModel.state
    val lazeListState = rememberLazyListState()
    viewModel.getFavoritesFromDB()
    val favorites = viewModel.favorites.collectAsStateWithLifecycle(listOf())

//    viewModel.getCharacters()
//    Column {
//
//        PasswordField(value = "ComicScreen", onChange = {}, submit = { /*TODO*/ })
//        PasswordField(value = "ComicScreen", onChange = {}, submit = { /*TODO*/ })
//        PasswordField(value = "ComicScreen", onChange = {}, submit = { /*TODO*/ })
//        PasswordField(value = "ComicScreen", onChange = {}, submit = { /*TODO*/ })
//        PasswordField(value = "ComicScreen", onChange = {}, submit = { /*TODO*/ })
//        Text(text = "ComicScreen")
//    }
    LazyColumn(
        state = lazeListState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//
        item {

            Spacer(modifier = Modifier.height(70.dp))
        }
        items(state.items.size) { i ->
            val item = state.items[i]
            val teste = favorites.value.contains(item)
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.getCharacters()
                viewModel.getFavoritesFromDB()
            }
            ComicItem(
                comic = item,
                modifier = Modifier.fillMaxWidth(),
                isFavoriteSelected = teste
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