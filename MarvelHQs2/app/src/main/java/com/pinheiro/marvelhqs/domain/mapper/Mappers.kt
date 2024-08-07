package com.pinheiro.marvelhqs.domain.mapper

import com.pinheiro.marvelhqs.data.repository.db.realm.model.ComicRealm
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO
import com.pinheiro.marvelhqs.data.repository.network.model.ImageDTO
import com.pinheiro.marvelhqs.domain.extensions.httpToHttps
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject


fun ComicDTO.comicDTOToComicViewObjectMapper() = ComicViewObject(
    id = id,
    title = title,
    issueNumber = issueNumber,
    variantDescription = variantDescription,
    description = description,
    pageCount = pageCount,
    thumbnail = "${
        thumbnail
            ?.path
            ?.httpToHttps()
    }.${thumbnail?.extension}"

)

fun ComicViewObject.comicViewObjectToComicDTOMapper() = ComicDTO(
    id = id,
    title = title,
    issueNumber = issueNumber,
    variantDescription = variantDescription,
    description = description,
    pageCount = pageCount,
    digitalId = null,
    modified = null, // type date on the server.
    isbn = null,
    upc = null,
    diamondCode = null,
    ean = null,
    issn = null,
    format = null,
    textObjects = null,
    resourceURI = null,
    urls = null,
    series = null,
    variants = null,
    collections = null,
    collectedIssues = null,
    dates = null,
    prices = null,
    thumbnail = ImageDTO(
        path = thumbnail?.substringBefore(".jpg"),
        extension = "jpg"
    ),
    images = null,
    creators = null,
    characters = null,
    stories = null,
    events = null

)

fun List<ComicDTO>.comicDTOListTOComicViewObjectList(): List<ComicViewObject> {
    return this.map { it.comicDTOToComicViewObjectMapper() }
}

fun ComicRealm.comicRealmToComicDTOMapper() = ComicDTO(
    id = _id,
    title = title,
    issueNumber = issueNumber,
    variantDescription = variantDescription,
    description = description,
    pageCount = pageCount,
    digitalId = null,
    modified = null, // type date on the server.
    isbn = null,
    upc = null,
    diamondCode = null,
    ean = null,
    issn = null,
    format = null,
    textObjects = null,
    resourceURI = null,
    urls = null,
    series = null,
    variants = null,
    collections = null,
    collectedIssues = null,
    dates = null,
    prices = null,
    thumbnail = ImageDTO(
        path = thumbnail?.substringBefore(".jpg"),
        extension = "jpg"
    ),
    images = null,
    creators = null,
    characters = null,
    stories = null,
    events = null
)


fun List<ComicRealm>.comicRealmListToComicDTOList(): List<ComicDTO> {
    return this.map { it.comicRealmToComicDTOMapper() }
}