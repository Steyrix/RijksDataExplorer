package com.example.rijksdataexplorer.core.data.response

data class CollectionResponse(
    val artObjects: List<ArtObjectDto>
)

data class ArtObjectDto(
    val id: String,
    val objectNumber: String,
    val title: String,
    val principalOrFirstMaker: String,
    val longTitle: String,
    val showImage: Boolean,
    val headerImage: HeaderImageDto
)

data class HeaderImageDto(
    val url: String
)