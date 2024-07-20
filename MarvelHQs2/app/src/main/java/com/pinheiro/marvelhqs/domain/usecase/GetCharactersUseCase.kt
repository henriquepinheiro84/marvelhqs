package com.pinheiro.marvelhqs.domain.usecase

import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.domain.mapper.comicDTOListTOComicViewObjectList
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import java.util.concurrent.TimeoutException

class GetCharactersUseCase(
    private val characterNetworkRepository: ICharacterRepository
) {


    suspend operator fun invoke(limit: String, offset: String): Result<List<ComicViewObject>> {
        try {
            val comicDataWrapper = characterNetworkRepository.getCharacter(limit, offset)
            if (comicDataWrapper.isSuccess) {

                val comicDataContainer = comicDataWrapper.getOrNull()?.data
                val comicViewObjectList =
                    comicDataContainer?.results?.comicDTOListTOComicViewObjectList()
                comicViewObjectList?.let { return Result.success(it) }
            }
            return Result.failure(comicDataWrapper.exceptionOrNull() ?: Exception("ERROR"))

        } catch (e: TimeoutException) {
            return Result.failure(e)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}