package com.pinheiro.marvelhqs

import com.google.common.truth.Truth
import com.pinheiro.marvelhqs.domain.usecase.ValidateLoginUseCase
import org.junit.Test

class LoginUnitTest {
    @Test
    fun `user name empty returns false`() {
        val result = ValidateLoginUseCase().invoke(
            "",
            "123",
        )
        Truth.assertThat(result).isFalse()

    }
    @Test
    fun `password empty returns false`() {
        val result = ValidateLoginUseCase().invoke(
            "teste",
            "",
        )
        Truth.assertThat(result).isFalse()

    }
    @Test
    fun `username that does not exist return false`() {
        val result = ValidateLoginUseCase().invoke(
            "teste",
            "admin",
        )
        Truth.assertThat(result).isFalse()

    }
    @Test
    fun `wrong password return false`() {
        val result = ValidateLoginUseCase().invoke(
            "admin",
            "123",
        )
        Truth.assertThat(result).isFalse()

    }
    @Test
    fun `valid username and password return true`() {
        val result = ValidateLoginUseCase().invoke(
            "admin",
            "admin",
        )
        Truth.assertThat(result).isTrue()

    } // Realizar teste de interfaces. - Até segunda feira.
    // Entregar os testes - Mandar para o cliente.
    // Posso corrigir o códiog também.
}