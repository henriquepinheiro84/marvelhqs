package com.pinheiro.marvelhqs.data.repository.db.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pinheiro.marvelhqs.data.repository.db.room.eintitys.ComicEntity

@Dao
interface ComicDao {
    @Insert
    fun saveComic(comicEntity: ComicEntity)
@Query("SELECT * FROM comicentity")
    fun getComics() : List<ComicEntity>
}