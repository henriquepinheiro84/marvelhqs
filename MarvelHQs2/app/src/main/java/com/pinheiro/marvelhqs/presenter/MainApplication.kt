package com.pinheiro.marvelhqs.presenter

import android.app.Application
import android.icu.util.LocaleData.PaperSize
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pinheiro.marvelhqs.data.repository.db.realm.model.ComicRealm
import com.pinheiro.marvelhqs.data.repository.db.realm.model.UserRealm
import com.pinheiro.marvelhqs.data.repository.db.room.AppDataBase
import com.pinheiro.marvelhqs.di.appModule
import io.paperdb.Paper
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        init()
        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

    }

    private fun init() {
        intPaperDb()
//        intRealmDb()
    }

    private fun intPaperDb() {
        Paper.init(this)
    }
//    private fun intRealmDb() {
//        realm = Realm.open(
//            configuration = RealmConfiguration.create(
//                schema = setOf(
//                    ComicRealm::class,
//                    UserRealm::class
//                )
//            )
//        )
//    }
//    private initRoomDb() {
//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDataBase::class.java, "database-name"
//        ).build()
//    }


}