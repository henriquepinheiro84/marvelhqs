package com.pinheiro.marvelhqs.domain.viewobject

import com.google.gson.annotations.SerializedName
import com.pinheiro.marvelhqs.data.repository.network.model.ImageDTO

data class ComicViewObject(
    val id: Int?,
    val title : String?,
    val issueNumber : Double?,
    val variantDescription: String?,
    val description: String?,
    val pageCount: Int?,
    val images: ArrayList<ImageDTO>?,
    val thumbnail: ImageDTO?,
)
