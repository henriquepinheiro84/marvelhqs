package com.pinheiro.marvelhqs.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class ComicDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("digitalId")
    val digitalId: Int?,
    @SerializedName("title")
    val title : String?,
    @SerializedName("issueNumber")
    val issueNumber : Double?,
    @SerializedName("variantDescription")
    val variantDescription: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String?, // type date on the server.
    @SerializedName("isbn")
    val isbn: String?,
    @SerializedName("upc")
    val upc: String?,
    @SerializedName("diamondCode")
    val diamondCode: String?,
    @SerializedName("ean")
    val ean: String?,
    @SerializedName("issn")
    val issn: String?,
    @SerializedName("format")
    val format: String?,
    @SerializedName("pageCount")
    val pageCount: Int?,
    @SerializedName("textObjects")
    val textObjects: ArrayList<TextObjectDTO>?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("urls")
    val urls: ArrayList<UrlDTO>?,
    @SerializedName("series")
    val series: SeriesSummaryDTO?,
    @SerializedName("variants")
    val variants: ArrayList<ComicSummaryDTO>?,
    @SerializedName("collections")
    val collections: ArrayList<ComicSummaryDTO>?,
    @SerializedName("collectedIssues")
    val collectedIssues: ArrayList<ComicSummaryDTO>?,
    @SerializedName("dates")
    val dates: ArrayList<ComicDateDTO>?,
    @SerializedName("prices")
    val prices: ArrayList<ComicPriceDTO>?,
    @SerializedName("thumbnail")
    val thumbnail: ImageDTO?,
    @SerializedName("images")
    val images: ArrayList<ImageDTO>?,
    @SerializedName("creators")
    val creators: CreatorListDTO?,
    @SerializedName("characters")
    val characters: CharacterListDTO?,
    @SerializedName("stories")
    val stories: StoryListDTO?,
    @SerializedName("events")
    val events: EventListDTO?
)
