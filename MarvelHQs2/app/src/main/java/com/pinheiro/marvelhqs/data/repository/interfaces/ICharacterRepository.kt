package com.pinheiro.marvelhqs.data.repository.interfaces

import com.pinheiro.marvelhqs.data.repository.network.response.CharacterResponse

interface ICharacterRepository {
suspend fun getCharacter(): CharacterResponse
}