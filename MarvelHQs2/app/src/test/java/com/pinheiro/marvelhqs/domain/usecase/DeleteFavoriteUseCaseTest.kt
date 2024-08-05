package com.pinheiro.marvelhqs.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.pinheiro.marvelhqs.data.repository.db.realm.FakeCharacterRealmRepository
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteFavoriteUseCaseTest {

    private lateinit var fakeCharacterRealmRepository: FakeCharacterRealmRepository
    private lateinit var deleteFavoriteUseCase: DeleteFavoriteUseCase
    @Before
    fun setUp() = runBlocking {
        fakeCharacterRealmRepository = FakeCharacterRealmRepository()
        deleteFavoriteUseCase = DeleteFavoriteUseCase(
            fakeCharacterRealmRepository
        )
        val comic = ComicDTO(
            id = 1,
            digitalId = null,
            title = "title 1",
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
    }

    @Test
    fun `delete favorite remove favorite from database`() = runBlocking {
        val comic = ComicDTO(
            id = 1,
            digitalId = null,
            title = "title 1",
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
        fakeCharacterRealmRepository.deleteComic(comic)
        fakeCharacterRealmRepository.getComics().collect {
            assertThat(it).isEmpty()
        }
    }
}