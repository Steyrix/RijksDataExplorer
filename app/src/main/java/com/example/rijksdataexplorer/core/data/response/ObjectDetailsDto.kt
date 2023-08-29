package com.example.rijksdataexplorer.core.data.response

data class CollectionDetailsResponse(
    val artObject: ObjectDetailsDto
)

data class ObjectDetailsDto(
    val id: String,
    val objectNumber: String,
    val webImage: WebImageDto,
    val titles: List<String>,
    val description: String,
    val principalMaker: String
)

data class WebImageDto(
    val url: String
)