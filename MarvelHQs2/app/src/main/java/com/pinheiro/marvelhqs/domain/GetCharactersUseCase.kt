package com.pinheiro.marvelhqs.domain

import com.pinheiro.marvelhqs.BuildConfig
import com.pinheiro.marvelhqs.data.repository.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.data.repository.network.CharacterNetworkImpl
import com.pinheiro.marvelhqs.data.repository.network.service.CharactersService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.MessageDigest

class GetCharactersUseCase(
    private val characterNetworkRepository: ICharacterRepository
) {
    @OptIn(ExperimentalStdlibApi::class)
    suspend operator fun invoke(): Flow<String> = flow {
         emit(characterNetworkRepository.getCharacter().copyright!!)

    }
}