package com.pinheiro.marvelhqs.data.repository.db.realm.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class ComicRealm : RealmObject {
    @PrimaryKey var _id: Int = 0
    var title : String?  = null
    var issueNumber : Double? = null
    var variantDescription: String? = null
    var description: String? = null
    var pageCount: Int? = null
    var thumbnail: String? = null
}