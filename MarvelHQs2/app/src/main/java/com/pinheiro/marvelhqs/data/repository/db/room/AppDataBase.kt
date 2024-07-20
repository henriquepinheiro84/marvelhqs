package com.pinheiro.marvelhqs.data.repository.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pinheiro.marvelhqs.data.repository.db.interfaces.ComicDao
import com.pinheiro.marvelhqs.data.repository.db.room.eintitys.ComicEntity

@Database(entities = [ComicEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun comicDao(): ComicDao

    abstract fun getComicDao(): ComicDao
}