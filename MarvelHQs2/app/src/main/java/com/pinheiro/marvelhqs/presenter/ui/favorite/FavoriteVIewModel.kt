package com.pinheiro.marvelhqs.presenter.ui.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.pinheiro.marvelhqs.domain.mapper.comicDTOListTOComicViewObjectList
import com.pinheiro.marvelhqs.domain.usecase.GetFavoriteUseCase
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import com.pinheiro.marvelhqs.presenter.DefaultPaginator
import com.pinheiro.marvelhqs.presenter.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FavoriteVIewModel(
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {

    var state by mutableStateOf(ScreenState())

    private val _favorites = MutableSharedFlow<List<ComicViewObject>>()
    val favorites = _favorites.asSharedFlow()
    init {
        getFavorites()
    }


//    private val paginator = DefaultPaginator(
//        initialKey = state.page,
//        onLoadUpdated = {
//            state = state.copy(isLoading = it)
//        },
//        onRequest = { nextPage ->
//
//
//        },
//        getNextKey = {
//            state.page + 16
//        },
//        onError = {
//            state = state.copy(error = it?.localizedMessage)
//        },
//        onSuccess = { items, newKey ->
//            state = state.copy(
//                items = state.items + items,
//                page = newKey,
//                endReached = items.isEmpty()
//            )
//        }
//    )
//    init {
//        viewModelScope.launch {
//            paginator.loadNextItems()
//        }
//    }
private fun getFavorites() {
       viewModelScope.launch {
           getFavoriteUseCase().collect{comicsDTO ->
               comicsDTO?.let {
                   _favorites.emit(it.comicDTOListTOComicViewObjectList())
               }
           }
       }

    }
}