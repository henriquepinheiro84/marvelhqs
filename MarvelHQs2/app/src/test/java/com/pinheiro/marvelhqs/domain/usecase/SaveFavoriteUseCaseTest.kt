package com.pinheiro.marvelhqs.domain.usecase


import com.google.common.truth.Truth.assertThat
import com.pinheiro.marvelhqs.data.repository.db.realm.FakeCharacterRealmRepository
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SaveFavoriteUseCaseTest {

    private lateinit var fakeCharacterRealmRepository: FakeCharacterRealmRepository
    private lateinit var saveFavoriteUseCase: SaveFavoriteUseCase

    @Before
    fun setUp() {
        fakeCharacterRealmRepository = FakeCharacterRealmRepository()
        saveFavoriteUseCase = SaveFavoriteUseCase(
            fakeCharacterRealmRepository
        )
    }

    @Test
    fun `save favorite update the list of usecases on database`() = runBlocking {

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
        fakeCharacterRealmRepository.getComics().collect {
            assertThat(it).isNotEmpty()
        }
    }

}