package com.pinheiro.marvelhqs.presentation.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinheiro.marvelhqs.domain.usecase.ValidateLoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUseCase: ValidateLoginUseCase
) : ViewModel() {

    private val _successLogin = MutableSharedFlow<Boolean>()
    val successLogin = _successLogin.asSharedFlow()
    fun validateUser(
        userName: String?,
        password: String?
    ): Boolean {
        val loginResult = loginUseCase(
            userName,
            password
        )
        viewModelScope.launch {
            _successLogin.emit(loginResult)
        }
        return loginResult
    }


}