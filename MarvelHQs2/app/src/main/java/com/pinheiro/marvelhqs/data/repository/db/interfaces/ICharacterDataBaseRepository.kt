package com.pinheiro.marvelhqs.data.repository.db.interfaces

import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import com.pinheiro.marvelhqs.presenter.Comics

interface ICharacterDataBaseRepository {
    fun saveComic(comicDTO: ComicDTO)
    fun getComics(): List<ComicDTO>
}