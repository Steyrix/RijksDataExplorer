package com.example.rijksdataexplorer.main.overview

import com.example.rijksdataexplorer.core.data.response.ArtObjectDto
import com.example.rijksdataexplorer.core.data.response.CollectionResponse
import com.example.rijksdataexplorer.core.data.response.HeaderImageDto
import okhttp3.ResponseBody
import retrofit2.Response

fun getInitialState() = OverviewScreenContract.State(
    items = emptyList(),
    currentPage = 1,
    isError = false,
    isLoading = true,
    listState = OverviewScreenContract.ListState.LOADING,
    canPaginate = false
)

fun getAfterSuccessStateWithoutPagination() = OverviewScreenContract.State(
    items = getCollectionResponse().artObjects,
    currentPage = 1,
    isError = false,
    isLoading = false,
    listState = OverviewScreenContract.ListState.IDLE,
    canPaginate = false
)

fun getAfterSuccessStateWithPagination() = OverviewScreenContract.State(
    items = getCollectionResponseWithManyItems().artObjects,
    currentPage = 2,
    isError = false,
    isLoading = false,
    listState = OverviewScreenContract.ListState.IDLE,
    canPaginate = true
)

fun getErrorState() = OverviewScreenContract.State(
    items = emptyList(),
    currentPage = 1,
    isError = true,
    isLoading = false,
    listState = OverviewScreenContract.ListState.ERROR,
    canPaginate = true
)

fun getPaginationExhausState() = OverviewScreenContract.State(
    items = getCollectionResponseWithManyItems().artObjects,
    currentPage = 2,
    isError = false,
    isLoading = false,
    listState = OverviewScreenContract.ListState.PAGINATING_EXHAUST,
    canPaginate = false
)

fun getCollectionResponse() = CollectionResponse(
    artObjects = listOf(
        getArtObject(),
        getArtObject(),
        getArtObject(),
        getArtObject(),
        getArtObject(),
    )
)

fun getCollectionResponseWithManyItems() = CollectionResponse(
    artObjects = List(20) { getArtObject() }
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

fun getCollectionResponseError(): Response<CollectionResponse> =
    Response.error(500, ResponseBody.Companion.create(null, ""))
