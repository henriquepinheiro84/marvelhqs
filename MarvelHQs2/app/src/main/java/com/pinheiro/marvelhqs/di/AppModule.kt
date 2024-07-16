package com.pinheiro.marvelhqs.di

import com.google.gson.GsonBuilder
import com.pinheiro.marvelhqs.data.repository.Constants.NETWORK_BASE_URL
import com.pinheiro.marvelhqs.data.repository.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.data.repository.network.CharacterNetworkImpl
import com.pinheiro.marvelhqs.data.repository.network.service.CharactersService
import com.pinheiro.marvelhqs.domain.usecase.GetCharactersUseCase
import com.pinheiro.marvelhqs.presenter.MarvelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
single<CharactersService> {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(NETWORK_BASE_URL)
        .build()
        .create(CharactersService::class.java)
}
    factory { GetCharactersUseCase(get()) }
    single {
        CharacterNetworkImpl(get())
    } bind ICharacterRepository::class

    viewModel {
        MarvelViewModel(get())
    }

}