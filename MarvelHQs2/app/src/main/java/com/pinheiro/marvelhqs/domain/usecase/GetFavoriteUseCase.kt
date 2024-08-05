package com.pinheiro.marvelhqs.domain.usecase

import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import kotlinx.coroutines.flow.Flow

class GetFavoriteUseCase(
    val repository: ICharacterDataBaseRepository
) {
    suspend operator fun invoke(): Flow<List<ComicDTO>?> = repository.getComics()

}