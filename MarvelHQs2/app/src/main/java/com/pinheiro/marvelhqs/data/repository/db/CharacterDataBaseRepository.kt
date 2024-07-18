package com.pinheiro.marvelhqs.data.repository.db

import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.data.repository.db.room.AppDataBase
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import com.pinheiro.marvelhqs.domain.mapper.comicDTOToComicEntityMapper
import com.pinheiro.marvelhqs.domain.mapper.comicEntityListToComicDTOList
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject

class CharacterDataBaseRepository(
    private val dataBase: AppDataBase
) : ICharacterDataBaseRepository {
    override fun saveComic(comicDTO: ComicDTO) {
        dataBase.comicDao().saveComic(comicDTO.comicDTOToComicEntityMapper())
    }

    override fun getComics(): List<ComicDTO> {
        val comics = dataBase.comicDao().getComics()
        return comics.comicEntityListToComicDTOList()
    }
}