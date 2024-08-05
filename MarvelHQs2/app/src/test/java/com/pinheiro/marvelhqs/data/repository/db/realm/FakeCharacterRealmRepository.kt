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
        return flow { emit(comics) }
    }

    override suspend fun deleteComic(comicDTO: ComicDTO) {
       comics.remove(comicDTO)
    }
}