package com.pinheiro.marvelhqs.data.repository.network.response

import com.google.gson.annotations.SerializedName
import com.pinheiro.marvelhqs.BuildConfig
import com.pinheiro.marvelhqs.data.repository.network.model.ComicDataContainerDTO

data class CharacterResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("data")
    val data: ComicDataContainerDTO?,
    @SerializedName("etag")
    val etag: String?,

    )
