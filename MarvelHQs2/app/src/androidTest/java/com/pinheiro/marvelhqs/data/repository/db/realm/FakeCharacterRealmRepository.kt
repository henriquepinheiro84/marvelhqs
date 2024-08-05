package com.pinheiro.marvelhqs.data.repository.db.realm

import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharacterRealmRepository : ICharacterDataBaseRepository {
    private var comics = mutableListOf<ComicDTO>()

    override suspend fun saveComic(comicDTO: ComicDTO) {
        comics.add(comicDTO)
    }

    override suspend fun getComics(): Flow<List<ComicDTO>?> {
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
            comics.add(comic)
        }
        return flow { emit(comics) }
    }

    override suspend fun deleteComic(comicDTO: ComicDTO) {
        comics.remove(comicDTO)
    }
}