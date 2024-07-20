package com.pinheiro.marvelhqs.presenter.ui.comic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pinheiro.marvelhqs.R
import com.pinheiro.marvelhqs.presenter.ui.navigation.Login
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComicScreen(mainNavController: NavHostController) {

    val viewModel: MarvelViewModel = koinViewModel()
    val state = viewModel.state
    val lazeListState = rememberLazyListState()
    viewModel.getFavoritesFromDB()
    val favorites = viewModel.favorites.collectAsStateWithLifecycle(listOf())

    LazyColumn(
        state = lazeListState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

    if (state.error?.isEmpty() == false) {
        Dialog(onDismissRequest = {
            mainNavController.navigate(Login) {
            popUpTo(0)
        }

        }) {
            Column(
                modifier = Modifier
                    .widthIn(200.dp, 300.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.RETRIVE_COMICS_FAILED_TITLE),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(R.string.RETRIVE_COMICS_FAILED_MESSAGE))
                Button(onClick = {
                    mainNavController.navigate(Login) {
                        popUpTo(0)
                    }
                }) {
                    Text(text = stringResource(R.string.UNDERSTAND))
                }

            }
        }
    }
}