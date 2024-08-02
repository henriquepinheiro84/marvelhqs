package com.pinheiro.marvelhqs.data.repository.db.realm

import com.pinheiro.marvelhqs.data.repository.db.realm.model.ComicRealm
import com.pinheiro.marvelhqs.data.repository.db.realm.model.UserRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmDbInstance {
    fun getRealmDB(): Realm {
        return  Realm.open(
            configuration = RealmConfiguration.Builder(
                schema = setOf(
                    ComicRealm::class
                )
            )
                .schemaVersion(1)
                .build()
        )
    }
}