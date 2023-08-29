package com.example.rijksdataexplorer.core.data.repository

import com.example.rijksdataexplorer.core.data.response.CollectionDetailsResponse
import com.example.rijksdataexplorer.core.data.response.CollectionResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response


class RijksRepositoryImplTest {

    private val service = mockk<RijksDataService>()

    private val repo = RijksRepositoryImpl(service)

    @Test
    fun `should call get collection`() {
        val expectedResponse = Response.success(getCollectionResponse())
        val expectedResult = Result.success(getCollectionResponse())

        coEvery {
            service.getCollection(any(), any())
        } returns expectedResponse

        var result: Result<CollectionResponse>

        runBlocking {
            result = repo.getData(0)
        }

        coVerify(exactly = 1) {
            service.getCollection(0)
        }

        assertEquals(result, expectedResult)
    }

    @Test
    fun `should return failure on get collection fail`() {
        val expectedResponse = getCollectionResponseError()

        coEvery {
            service.getCollection(any(), any())
        } returns expectedResponse

        var result: Result<CollectionResponse>

        runBlocking {
            result = repo.getData(0)
        }

        assert(result.isFailure)
    }

    @Test
    fun `should call get details`() {
        val expectedResponse = Response.success(getCollectionDetailsResponse())
        val expectedResult = Result.success(getCollectionDetailsResponse())
        val expectedNumber = "SKA-99"

        coEvery {
            service.getObjectDetails(any())
        } returns expectedResponse

        var result: Result<CollectionDetailsResponse>

        runBlocking {
            result = repo.getDetails(expectedNumber)
        }

        coVerify(exactly = 1) {
            service.getObjectDetails(expectedNumber)
        }

        assertEquals(result, expectedResult)
    }

    @Test
    fun `should return failure on get details fail`() {
        val expectedResponse = getDetailsResponseError()

        coEvery {
            service.getObjectDetails(any())
        } returns expectedResponse

        var result: Result<CollectionDetailsResponse>

        runBlocking {
            result = repo.getDetails("SOMETHING")
        }

        assert(result.isFailure)
    }
}