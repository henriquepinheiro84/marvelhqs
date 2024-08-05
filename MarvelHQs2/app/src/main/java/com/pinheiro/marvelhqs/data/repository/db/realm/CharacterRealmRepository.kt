package com.pinheiro.marvelhqs.data.repository.db.realm

import com.pinheiro.marvelhqs.data.repository.db.interfaces.ICharacterDataBaseRepository
import com.pinheiro.marvelhqs.data.repository.db.realm.model.ComicRealm
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import com.pinheiro.marvelhqs.domain.mapper.comicRealmListToComicDTOList
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRealmRepository : ICharacterDataBaseRepository {

    override suspend fun saveComic(comicDTO: ComicDTO) {
        RealmDbInstance.getRealmDB().write {
            val comic = ComicRealm().apply {
                comicDTO.id?.let { _id = it }
                comicDTO.title?.let { title = it }
                comicDTO.issueNumber?.let { issueNumber = it }
                comicDTO.variantDescription?.let { variantDescription = it }
                comicDTO.description?.let { description = it }
                comicDTO.thumbnail?.let { thumbnail = "${it.path}.${it.extension}" }
            }
            copyToRealm(instance = comic, updatePolicy = UpdatePolicy.ALL)
        }
    }

    override suspend fun getComics(): Flow<List<ComicDTO>?> {
        return RealmDbInstance.getRealmDB().query<ComicRealm>().asFlow().map { result -> result.list.toList().comicRealmListToComicDTOList() }
    }

    override suspend fun deleteComic(comicDTO: ComicDTO) {

        RealmDbInstance.getRealmDB().write {
            val comicToDelete: ComicRealm = query<ComicRealm>("_id == $0", comicDTO.id).find().first()

            delete(comicToDelete)
        }
    }

}