package com.pinheiro.marvelhqs.data.repository.db.interfaces

import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import kotlinx.coroutines.flow.Flow

interface ICharacterDataBaseRepository {
   suspend fun saveComic(comicDTO: ComicDTO )
    suspend fun getComics(): Flow<List<ComicDTO>?>

    suspend fun deleteComic(comicDTO: ComicDTO)
}