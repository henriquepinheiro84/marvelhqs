package com.pinheiro.marvelhqs.presentation.ui.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.marvelhqs.domain.usecase.DeleteFavoriteUseCase
import com.pinheiro.marvelhqs.domain.usecase.SaveFavoriteUseCase
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import kotlinx.coroutines.launch

class ComicItemViewModel(
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    fun saveFavorite(comicViewObject: ComicViewObject) {
        viewModelScope.launch {
            println("Save ${comicViewObject.id}")
            saveFavoriteUseCase(comicViewObject)
        }
    }

    fun deleteFavorite(comicViewObject: ComicViewObject?) {
        viewModelScope.launch {
            println("Delete ${comicViewObject?.id}")
            comicViewObject?.let { deleteFavoriteUseCase(it) }
        }
    }

}