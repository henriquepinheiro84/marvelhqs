package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class CreatorListDTO(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("returned")
    val returned: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: ArrayList<CreatorSummaryDTO>?
)