package com.pinheiro.marvelhqs.domain.usecase

import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.domain.mapper.comicDTOListTOComicViewObjectList
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import java.util.concurrent.TimeoutException

class GetCharactersUseCase(
    private val characterNetworkRepository: ICharacterRepository,
    private val getServerHashUseCase: GetServerHashUseCase
) {

    suspend operator fun invoke(limit: String, offset: String): Result<List<ComicViewObject>> {
        try {
            val ts = "1"
            val hash = getServerHashUseCase(ts)
//            val publicKey = BuildConfig.PUBLIC_KEY
//            val privateKey = BuildConfig.PRIVATE_KEY
//            val codeToHash = ts + privateKey + publicKey
//            val md = MessageDigest.getInstance("MD5")
//            val hash = md.digest(codeToHash.toByteArray()).toHexString()
            val comicDataWrapper = characterNetworkRepository.getCharacter(limit, offset, ts, hash)
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