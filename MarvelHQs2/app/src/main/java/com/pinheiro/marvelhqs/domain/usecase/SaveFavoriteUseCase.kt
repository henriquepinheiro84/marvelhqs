package com.pinheiro.marvelhqs.domain.usecase

import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.domain.mapper.comicViewObjectToComicDTOMapper
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject

class SaveFavoriteUseCase(
    val repository: ICharacterDataBaseRepository
) {
    suspend operator fun invoke(comicViewObject: ComicViewObject) {
     repository.saveComic(comicViewObject.comicViewObjectToComicDTOMapper())

    }
}