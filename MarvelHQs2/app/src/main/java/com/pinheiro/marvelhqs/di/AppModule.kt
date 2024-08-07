package com.pinheiro.marvelhqs.di

import com.google.gson.GsonBuilder
import com.pinheiro.marvelhqs.BuildConfig
import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.data.repository.db.realm.CharacterRealmRepository
import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.data.repository.network.CharacterNetworkImpl
import com.pinheiro.marvelhqs.data.repository.network.service.CharactersService
import com.pinheiro.marvelhqs.domain.usecase.DeleteFavoriteUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetCharactersUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetFavoriteUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetServerHashUseCase
import com.pinheiro.marvelhqs.domain.usecase.SaveFavoriteUseCase
import com.pinheiro.marvelhqs.domain.usecase.ValidateLoginUseCase
import com.pinheiro.marvelhqs.presentation.ui.comic.MarvelViewModel
import com.pinheiro.marvelhqs.presentation.ui.authentication.LoginViewModel
import com.pinheiro.marvelhqs.presentation.ui.comic.ComicItemViewModel
import com.pinheiro.marvelhqs.presentation.ui.favorite.FavoriteVIewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
const val TIME_OUT: Long = 30
val appModule = module {
single<CharactersService> {
    Retrofit.Builder().client(OkHttpClient().newBuilder()
        .connectTimeout(TIME_OUT, TimeUnit.MINUTES)
        .writeTimeout(TIME_OUT, TimeUnit.MINUTES)
        .readTimeout(TIME_OUT, TimeUnit.MINUTES)
        .build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create(CharactersService::class.java)
}
    factory { GetCharactersUseCase(get(), get()) }
    single {
        CharacterNetworkImpl(get())
    } bind ICharacterRepository::class

    viewModel {
        MarvelViewModel(get(), get())
    }
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        ComicItemViewModel(get(), get())
    }



    factory { SaveFavoriteUseCase(get()) }
    factory { FavoriteVIewModel(get()) }
    factory { GetFavoriteUseCase(get()) }
    factory { DeleteFavoriteUseCase(get()) }
    factory { ValidateLoginUseCase() }
    factory { GetServerHashUseCase() }
    factory { CharacterRealmRepository() } bind ICharacterDataBaseRepository::class

}