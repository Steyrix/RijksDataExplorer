package com.example.rijksdataexplorer.main.item_details

import com.example.rijksdataexplorer.core.data.response.CollectionDetailsResponse
import com.example.rijksdataexplorer.core.data.response.ObjectDetailsDto
import com.example.rijksdataexplorer.core.data.response.WebImageDto

fun getInitialState() = ItemDetailsScreenContract.State(
    item = null,
    isError = false,
    isLoading = false
)

fun getSuccesslState() = ItemDetailsScreenContract.State(
    item = getObjectDetails(),
    isError = false,
    isLoading = false
)

fun getErrorState() = ItemDetailsScreenContract.State(
    item = null,
    isError = true,
    isLoading = false
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