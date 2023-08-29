package com.example.rijksdataexplorer.core.data.repository

import com.example.rijksdataexplorer.core.data.response.CollectionDetailsResponse
import com.example.rijksdataexplorer.core.data.response.CollectionResponse

interface RijksRepository {

    suspend fun getData(page: Int = 0): Result<CollectionResponse>

    suspend fun getDetails(objectNumber: String): Result<CollectionDetailsResponse>
}