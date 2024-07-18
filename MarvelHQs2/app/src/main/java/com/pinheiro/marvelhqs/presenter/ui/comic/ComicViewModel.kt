package com.pinheiro.marvelhqs.presenter.ui.comic

import androidx.lifecycle.ViewModel
import com.pinheiro.marvelhqs.domain.usecase.SaveFavoriteUseCase

class ComicViewModel(
    val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModel() {

}