package com.pinheiro.marvelhqs.data.repository.network

import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDataContainerDTO
import com.pinheiro.marvelhqs.data.repository.network.response.CharacterResponse

class FakeCharacterRepository : ICharacterRepository {
    private val characters = mutableListOf<ComicDTO>()

    private fun setListOfCharacters() {
        (0..5).forEachIndexed { index, i ->
           characters.add(ComicDTO(
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
           )

        }
    }

    override suspend fun getCharacter(
        limit: String,
        offset: String,
        ts: String,
        hash: String
    ): Result<CharacterResponse> {
        setListOfCharacters()
        return Result.success(
            CharacterResponse(
                1,
                "",
                "",
                "",
                "", ComicDataContainerDTO(
                    0,
                    0,
                    0,
                    characters

                ),
                ""
            )
        )
    }
}