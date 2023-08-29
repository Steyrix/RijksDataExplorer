package com.example.rijksdataexplorer.core.data.repository

import com.example.rijksdataexplorer.core.data.response.CollectionDetailsResponse
import com.example.rijksdataexplorer.core.data.response.CollectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RijksDataService {

    @GET("collection")
    suspend fun getCollection(
        @Query("p") page: Int,
        @Query("ps") pageCount: Int = 20
    ): Response<CollectionResponse>

    @GET("collection/{object-number}")
    suspend fun getObjectDetails(
        @Path("object-number") objectNumber: String
    ): Response<CollectionDetailsResponse>
}