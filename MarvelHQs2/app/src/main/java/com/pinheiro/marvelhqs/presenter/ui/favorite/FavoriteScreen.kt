package com.pinheiro.marvelhqs.presenter.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pinheiro.marvelhqs.presenter.ui.comic.ComicItem
import com.pinheiro.marvelhqs.presenter.ui.components.PasswordField
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen() {
    val viewModel: FavoriteVIewModel = koinViewModel()
    val state = viewModel.state
    val lazeListState = rememberLazyListState()
//    viewModel.getCharacters()


    val favorites = viewModel.favorites.collectAsStateWithLifecycle(listOf())
    LazyColumn(
        state = lazeListState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(72.dp))
        }
        items(favorites.value.size) { i ->
            val item = favorites.value[i]
            //            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
            //                viewModel.getCharacters()
            //            }
            ComicItem(
                comic = item,
                modifier = Modifier.fillMaxWidth(),
                isFavoriteSelected = true
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

@Composable
fun TestScreen() {
    Column {
        PasswordField(value = "teste", onChange = {}, submit = { /*TODO*/ })
        PasswordField(value = "teste", onChange = {}, submit = { /*TODO*/ })
        PasswordField(value = "teste", onChange = {}, submit = { /*TODO*/ })
        PasswordField(value = "teste", onChange = {}, submit = { /*TODO*/ })
        PasswordField(value = "teste", onChange = {}, submit = { /*TODO*/ })
    }
}


@Preview
@Composable
fun Previewteste() {
    FavoriteScreen()
}