package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class ComicDataContainerDTO(
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("results")
    val results : List<ComicDTO>
)
