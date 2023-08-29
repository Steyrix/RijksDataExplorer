package com.example.rijksdataexplorer.core.data.repository

import com.example.rijksdataexplorer.core.data.response.ArtObjectDto
import com.example.rijksdataexplorer.core.data.response.CollectionDetailsResponse
import com.example.rijksdataexplorer.core.data.response.CollectionResponse
import com.example.rijksdataexplorer.core.data.response.HeaderImageDto
import com.example.rijksdataexplorer.core.data.response.ObjectDetailsDto
import com.example.rijksdataexplorer.core.data.response.WebImageDto
import okhttp3.ResponseBody
import retrofit2.Response

fun getCollectionResponse() = CollectionResponse(
    artObjects = listOf(
        getArtObject(),
        getArtObject(),
        getArtObject(),
        getArtObject(),
        getArtObject(),
    )
)

fun getArtObject() = ArtObjectDto(
    id = "id",
    objectNumber = "objectNumber",
    title = "title",
    principalOrFirstMaker = "principal",
    longTitle = "longTitle",
    showImage = true,
    headerImage = HeaderImageDto("www.something.com")
)

fun getCollectionDetailsResponse() = CollectionDetailsResponse(
    artObject = getObjectDetails()
)

fun getObjectDetails() = ObjectDetailsDto(
    id = "id",
    objectNumber = "objectNumber",
    webImage = WebImageDto("www.something.com"),
    titles = listOf(""),
    description = "description",
    principalMaker = "principal"
)

fun getCollectionResponseError(): Response<CollectionResponse> =
    Response.error(500, ResponseBody.Companion.create(null, ""))

fun getDetailsResponseError(): Response<CollectionDetailsResponse> =
    Response.error(500, ResponseBody.Companion.create(null, ""))