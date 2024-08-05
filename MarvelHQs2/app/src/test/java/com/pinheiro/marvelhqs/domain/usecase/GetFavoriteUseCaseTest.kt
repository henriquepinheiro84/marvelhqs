package com.pinheiro.marvelhqs.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.pinheiro.marvelhqs.data.repository.db.realm.FakeCharacterRealmRepository
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetFavoriteUseCaseTest {

    private lateinit var fakeCharacterRealmRepository: FakeCharacterRealmRepository
    private lateinit var getFavoriteUseCase: GetFavoriteUseCase

    @Before
    fun setUp() = runBlocking {
        fakeCharacterRealmRepository = FakeCharacterRealmRepository()
        (0..3).forEachIndexed { index, i ->
            val comic = ComicDTO(
                id = index,
                digitalId = null,
                title = "title $i",
                issueNumber = null,
                variantDescription = "",
                description = "",
                modified = null,
                isbn = "",
                upc = "",
                diamondCode = "",
                ean = "",
                issn = "",
                format = "",
                pageCount = 22,
                textObjects = null,
                resourceURI = null,
                urls = null,
                series = null,
                variants = null,
                collections = null,
                collectedIssues = null,
                dates = null,
                prices = null,
                thumbnail = null,
                images = null,
                creators = null,
                characters = null,
                stories = null,
                events = null,
            )
            fakeCharacterRealmRepository.saveComic(comic)
            getFavoriteUseCase = GetFavoriteUseCase(
                fakeCharacterRealmRepository
            )
        }
    }

    @Test
    fun `get favorites reurns lis of favorites`() = runBlocking {
        assertThat(getFavoriteUseCase()).isNotNull()
    }
}