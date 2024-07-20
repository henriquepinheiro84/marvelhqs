package com.pinheiro.marvelhqs.presenter.ui.comic

import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<ComicViewObject> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)