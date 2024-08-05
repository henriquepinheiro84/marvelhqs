package com.pinheiro.marvelhqs.di

import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.data.repository.db.realm.FakeCharacterRealmRepository
import com.pinheiro.marvelhqs.data.repository.network.FakeCharacterRepository
import com.pinheiro.marvelhqs.data.repository.network.interfaces.ICharacterRepository
import com.pinheiro.marvelhqs.domain.usecase.DeleteFavoriteUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetCharactersUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetFavoriteUseCase
import com.pinheiro.marvelhqs.domain.usecase.GetServerHashUseCase
import com.pinheiro.marvelhqs.domain.usecase.SaveFavoriteUseCase
import com.pinheiro.marvelhqs.domain.usecase.ValidateLoginUseCase
import com.pinheiro.marvelhqs.presentation.ui.authentication.LoginViewModel
import com.pinheiro.marvelhqs.presentation.ui.comic.ComicItemViewModel
import com.pinheiro.marvelhqs.presentation.ui.comic.MarvelViewModel
import com.pinheiro.marvelhqs.presentation.ui.favorite.FavoriteVIewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val testAppModule = module {


    factory { GetCharactersUseCase(get(), get()) }
    single {
        FakeCharacterRepository()
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
    factory { FakeCharacterRealmRepository() } bind ICharacterDataBaseRepository::class
}