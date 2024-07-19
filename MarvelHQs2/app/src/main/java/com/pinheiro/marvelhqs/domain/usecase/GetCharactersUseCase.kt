package com.pinheiro.marvelhqs.domain.usecase

import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.domain.DataState
import com.pinheiro.marvelhqs.domain.Errors
import com.pinheiro.marvelhqs.domain.mapper.comicDTOListTOComicViewObjectList
import com.pinheiro.marvelhqs.domain.mapper.comicDataContainerDTOToComicDataContainerViewObjectMapper
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeoutException

class GetCharactersUseCase(
    private val characterNetworkRepository: ICharacterRepository
) {


    suspend operator fun invoke(limit: String, offset: String): Result<List<ComicViewObject>> {
//         emit(characterNetworkRepository.getCharacter().code.toString())
//        return DataState.loading()
        try {
            val comicDataWrapper = characterNetworkRepository.getCharacter(limit, offset)
            val responseCode = comicDataWrapper.code
            if (isResponseError(responseCode)) {
//            return DataState.error(getErrorMessage(responseCode))
            }
            val comicDataContainer = comicDataWrapper.data
            val comicViewObjectList = comicDataContainer?.results?.comicDTOListTOComicViewObjectList()
            comicViewObjectList?.let { return Result.success(it) }
//        return DataState.error("")
            val mock = arrayListOf(ComicViewObject(
                id = null,
                title = null,
                issueNumber = null,
                variantDescription = null,
                description = null,
                pageCount = null,
            ))
            return Result.success(mock)

        } catch (e: TimeoutException) {
            return Result.failure(e)
        }



    }

    private fun isResponseError(code: Int?): Boolean {
        return when(code) {
            200 -> false
            401 -> true
            403 -> true
            500 -> true
            else -> true

        }
    }

    private fun  getErrorMessage(code: Int?) : String {
        return when(code) {
            Errors.ServerError.code -> Errors.ServerError.message
            Errors.UnoutorizedError.code -> Errors.UnoutorizedError.message
            Errors.UknownError.code -> Errors.UknownError.message
            else -> Errors.UknownError.message
        }
    }
}