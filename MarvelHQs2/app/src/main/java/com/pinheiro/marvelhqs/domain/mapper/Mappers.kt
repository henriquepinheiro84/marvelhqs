package com.pinheiro.marvelhqs.domain.mapper

import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDataContainerDTO
import com.pinheiro.marvelhqs.domain.viewobject.ComicDataContainerViewObject
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject

fun ComicDataContainerDTO.comicDataContainerDTOToComicDataContainerViewObjectMapper() =
    ComicDataContainerViewObject(
        this.results
    )

fun ComicDTO.comicDTOToComicViewObjectMapper() = ComicViewObject(
    id = id,
    title = title,
    issueNumber = issueNumber,
    variantDescription = variantDescription,
    description = description,
    pageCount = pageCount,
    images = images,
    thumbnail = thumbnail

    )

fun Array<ComicDTO>.comicDTOListTOComicViewObjectList(): List<ComicViewObject> {
    return this.map { it.comicDTOToComicViewObjectMapper() }
}