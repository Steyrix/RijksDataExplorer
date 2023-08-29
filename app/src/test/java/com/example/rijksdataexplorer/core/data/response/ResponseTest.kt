package com.example.rijksdataexplorer.core.data.response

import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ResponseTest {

    private val gson = Gson()

    @Test
    fun `should deserialize collection json correctly`() {
        assertEquals(
            gson.fromJson(getCollectionResponseJson(), CollectionResponse::class.java),
            getCollectionResponse()
        )
    }

    @Test
    fun `should deserialize collection details json correctly`() {
        assertEquals(
            gson.fromJson(getCollectionDetailsResponseJson(), CollectionDetailsResponse::class.java),
            getCollectionDetailsResponse()
        )
    }
}