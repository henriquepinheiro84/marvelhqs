package com.pinheiro.marvelhqs.presentation.ui.authentication

import androidx.lifecycle.ViewModel
import com.pinheiro.marvelhqs.domain.usecase.ValidateLoginUseCase

class LoginViewModel(
    val loginUseCase: ValidateLoginUseCase
) : ViewModel() {

    fun validateUser(
        userName: String?,
        password: String?
    ): Boolean {
        val loginResult = loginUseCase(
            userName,
            password
        )
        return loginResult
    }


}