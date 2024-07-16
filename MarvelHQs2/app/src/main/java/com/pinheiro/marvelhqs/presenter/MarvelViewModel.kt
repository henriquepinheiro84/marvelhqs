package com.pinheiro.marvelhqs.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pinheiro.marvelhqs.domain.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MarvelViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _test = MutableSharedFlow<String>()
    val test = _test.asSharedFlow()
    var teste by mutableStateOf("")
        private set
    fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase().collect {
               _test.emit(it)
            }
        }
    }
}