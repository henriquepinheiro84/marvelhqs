package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class TextObjectDTO(
    @SerializedName("type")
    val type: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("text")
    val text: String
)
