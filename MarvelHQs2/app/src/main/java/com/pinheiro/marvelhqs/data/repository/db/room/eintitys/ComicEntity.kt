package com.pinheiro.marvelhqs.data.repository.db.room.eintitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ComicEntity(
   @PrimaryKey val id: Int?,
    val title : String?,
    val issueNumber : Double?,
    val variantDescription: String?,
    val description: String?,
    val pageCount: Int?,
)