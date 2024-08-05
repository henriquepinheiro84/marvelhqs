package com.pinheiro.marvelhqs.presentation.ui.comic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.marvelhqs.domain.mapper.comicDTOListTOComicViewObjectList
import com.pinheiro.marvelhqs.domain.usecase.GetCharactersUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetFavoriteUseCase
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import com.pinheiro.marvelhqs.presentation.DefaultPaginator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

private const val NUMBER_OF_ITEMS = "15"

class MarvelViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
) : ViewModel() {

    private val _favorites = MutableSharedFlow<List<ComicViewObject>>()
    val favorites = _favorites.asSharedFlow()


    var state by mutableStateOf(ScreenState())
    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
           try {
            getCharactersUseCase(NUMBER_OF_ITEMS, nextPage.toString())
           } catch (e: Exception) {
               Result.failure(e)

           }
        },
        getNextKey = {
            state.page + 16
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        getFavoritesFromDB()
        loadNextItems()
    }

    private fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun getCharacters() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun getFavoritesFromDB() {
        viewModelScope.launch {
            getFavoriteUseCase().collect { comicsDTO ->
                comicsDTO?.let {
                    _favorites.emit(it.comicDTOListTOComicViewObjectList())
                }
            }
        }
    }

}





