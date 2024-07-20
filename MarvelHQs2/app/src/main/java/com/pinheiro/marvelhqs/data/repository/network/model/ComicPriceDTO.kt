package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class ComicPriceDTO(
    @SerializedName("type")
    val type: String?,
    @SerializedName("price")
    val price : Float?
)
