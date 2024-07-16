package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class ImageDTO(
    @SerializedName("path")
    val path: String?,
    @SerializedName("extension")
    val extension: String?
)
