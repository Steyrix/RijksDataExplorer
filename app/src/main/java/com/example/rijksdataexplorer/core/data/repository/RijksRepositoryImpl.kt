package com.example.rijksdataexplorer.core.data.repository

import com.example.rijksdataexplorer.core.data.response.CollectionDetailsResponse
import com.example.rijksdataexplorer.core.data.response.CollectionResponse
import javax.inject.Inject

class RijksRepositoryImpl
@Inject constructor(
    private val service: RijksDataService
) : RijksRepository, SafeApiCallRepository {

    override suspend fun getData(
        page: Int
    ): Result<CollectionResponse> {
        return apiCall {
            service.getCollection(page)
        }
    }

    override suspend fun getDetails(
        objectNumber: String
    ): Result<CollectionDetailsResponse> {
        return apiCall {
            service.getObjectDetails(objectNumber)
        }
    }
}