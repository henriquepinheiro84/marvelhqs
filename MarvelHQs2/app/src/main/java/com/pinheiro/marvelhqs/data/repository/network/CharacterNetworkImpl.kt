package com.pinheiro.marvelhqs.data.repository.network

import com.pinheiro.marvelhqs.BuildConfig
import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.data.repository.network.response.CharacterResponse
import com.pinheiro.marvelhqs.data.repository.network.service.CharactersService
import java.security.MessageDigest

class CharacterNetworkImpl(
    val charactersService: CharactersService
) : ICharacterRepository {
    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun getCharacter(limit: String, offset: String): CharacterResponse {
//        val limit = "20"
//        val offset = "0"
        val ts = "1"
        val publicKey = BuildConfig.PUBLIC_KEY
        val privateKey = BuildConfig.PRIVATE_KEY
        val codeToHash = ts + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5")
        val hash = md.digest(codeToHash.toByteArray())

        return charactersService.getCharacteres(limit,offset,ts, BuildConfig.PUBLIC_KEY,hash.toHexString())
    }
}