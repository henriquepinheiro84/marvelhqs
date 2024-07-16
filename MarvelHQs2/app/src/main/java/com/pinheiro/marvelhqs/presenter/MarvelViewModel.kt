package com.pinheiro.marvelhqs.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.marvelhqs.domain.DataState
import com.pinheiro.marvelhqs.domain.usecase.GetCharactersUseCase
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MarvelViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _comics = MutableSharedFlow<List<ComicViewObject>>()
    val comics = _comics.asSharedFlow()
    fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase().collect {
                if (it.error?.isNotEmpty() == true) {
                    _error.emit(it.error)
                    return@collect
                }
                it.data?.let { comicsList -> _comics.emit(comicsList) }
            }
        }
    }
}