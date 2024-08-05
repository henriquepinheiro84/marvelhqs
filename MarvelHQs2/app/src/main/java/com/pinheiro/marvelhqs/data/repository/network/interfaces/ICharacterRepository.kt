package com.pinheiro.marvelhqs.data.repository.network.interfaces

import com.pinheiro.marvelhqs.data.repository.network.response.CharacterResponse

interface ICharacterRepository {
suspend fun getCharacter(limit: String, offset: String, ts: String, hash: String):Result<CharacterResponse>
}