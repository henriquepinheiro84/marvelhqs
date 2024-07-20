package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class UrlDTO(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
)
