package com.pinheiro.marvelhqs.data.repository.db.paper

import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.data.repository.db.room.Constants.COMIC_TABLE
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import io.paperdb.Paper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterDataBaseRepository(
) : ICharacterDataBaseRepository {
    override suspend fun saveComic(comicDTO: ComicDTO) {
        Paper.book().write(COMIC_TABLE, comicDTO)
    }

    override suspend fun getComics(): Flow<List<ComicDTO>?> {
        val comics = Paper.book().read<ArrayList<ComicDTO>>(COMIC_TABLE, arrayListOf())
        return flow{

        }
    }

    override suspend fun deleteComic(comicDTO: ComicDTO) {

        val comics = Paper.book().read<ArrayList<ComicDTO>?>(COMIC_TABLE, null)
        comics?.remove(comicDTO)
        comics?.let{
            Paper.book().write(COMIC_TABLE, it)
        }
    }
}