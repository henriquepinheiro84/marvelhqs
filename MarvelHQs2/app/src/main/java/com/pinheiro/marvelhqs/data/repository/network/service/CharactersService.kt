package com.pinheiro.marvelhqs.data.repository.network.service

import com.pinheiro.marvelhqs.data.repository.network.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val CHARACTERS_ENDPOINT = "/v1/public/comics"
private const val PUBLIC_KEY = "feff6e4c7c7169aa3d4a6f4a2b4aad2b"
private const val PRIVATE_KEY = "0a067122b7e1c24ce6840e53676602c88646b172"
interface CharactersService {
    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacteres(@Query("ts") ts: String, @Query("apikey") publicKey: String, @Query("hash") hash: String): CharacterResponse


}