package com.pinheiro.marvelhqs.data.repository.network

import com.pinheiro.marvelhqs.BuildConfig
import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.data.repository.network.response.CharacterResponse
import com.pinheiro.marvelhqs.data.repository.network.service.CharactersService
import java.security.MessageDigest

class CharacterNetworkImpl(
    private val charactersService: CharactersService
) : ICharacterRepository {
    override suspend fun getCharacter(limit: String, offset: String, ts: String, hash: String): Result<CharacterResponse> {

        return Result.success(charactersService.getCharacters(limit,offset,ts, BuildConfig.PUBLIC_KEY,hash))
    }
}