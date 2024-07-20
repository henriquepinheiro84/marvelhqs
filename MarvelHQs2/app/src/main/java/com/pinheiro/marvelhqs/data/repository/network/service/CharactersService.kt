package com.pinheiro.marvelhqs.data.repository.network.service

import com.pinheiro.marvelhqs.data.repository.network.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val CHARACTERS_ENDPOINT = "/v1/public/comics"

interface CharactersService {
    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacters(
        @Query("limit") limit: String,
        @Query("offset") offset: String,
        @Query("ts") ts: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String
    ): CharacterResponse


}