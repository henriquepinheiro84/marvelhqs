package com.pinheiro.marvelhqs.domain.viewobject

import com.pinheiro.marvelhqs.data.repository.network.model.ComicDTO

data class ComicDataContainerViewObject(
    val results : Array<ComicDTO>
)
