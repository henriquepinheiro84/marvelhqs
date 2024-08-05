package com.pinheiro.marvelhqs.domain.usecase

import com.google.common.truth.Truth
import com.pinheiro.marvelhqs.data.repository.network.FakeCharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {
    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var fakeCharacterRepository: FakeCharacterRepository

    @Before
    fun setUp() {
        fakeCharacterRepository = FakeCharacterRepository()
        getCharactersUseCase = GetCharactersUseCase(
            fakeCharacterRepository,
            getServerHashUseCase = GetServerHashUseCase()
        )
    }

    @Test
    fun `success on getting characters return carachers`() = runBlocking {
        val test = getCharactersUseCase(
            limit = "15",
            offset = "0"
        )
        Truth.assertThat(test.getOrNull()).isNotEmpty()
    }
}