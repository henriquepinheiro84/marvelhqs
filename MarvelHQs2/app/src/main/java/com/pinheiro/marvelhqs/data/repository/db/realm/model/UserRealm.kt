package com.pinheiro.marvelhqs.data.repository.db.realm.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserRealm : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String? = null
    var userName: String? = null
    var password: String? = null
}