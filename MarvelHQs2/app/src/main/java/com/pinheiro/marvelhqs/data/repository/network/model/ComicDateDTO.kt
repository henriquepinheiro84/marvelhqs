package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class ComicDateDTO(
    @SerializedName("type")
    val type: String?,
    @SerializedName("date")
    val date: String? // In the api it's a date type.
)
