package com.pinheiro.marvelhqs.presentation.ui.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.marvelhqs.domain.mapper.comicDTOListTOComicViewObjectList
import com.pinheiro.marvelhqs.domain.usecase.GetFavoriteUseCase
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import com.pinheiro.marvelhqs.presentation.ui.comic.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteVIewModel(
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {

    var state by mutableStateOf(ScreenState())

    private val _favorites = MutableStateFlow<List<ComicViewObject>>(listOf())
    val favorites = _favorites.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getFavoriteUseCase().collect { comicsDTO ->
                comicsDTO?.let {
                    _favorites.emit(it.comicDTOListTOComicViewObjectList())
                }
            }
        }

    }
}