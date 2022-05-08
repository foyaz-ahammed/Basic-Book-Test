package com.kou.example

import com.kou.example.entities.DataResult
import com.kou.example.entities.LoadResult
import org.junit.Assert.*
import org.junit.Test
import java.lang.NumberFormatException

/**
 * Functional tests for entity classes
 */
class EntitiesTest {
    @Test
    fun checkDataResultSuccess() {
        val data = "example data"
        val successResult = DataResult.SUCCESS(data)
        assertEquals("Compare string success result", data, successResult.data)
    }

    @Test
    fun checkDataResultFailure() {
        val exception = NumberFormatException("Invalid number")
        val failureResult = DataResult.FAILURE<String>(exception)
        assertEquals("Compare failure result exception", exception, failureResult.exception)
    }

    @Test
    fun checkLoaderStatusUniqueness() {
        assertNotEquals("Compare loading and success", LoadResult.LOADING, LoadResult.SUCCESS)
        assertNotEquals("Compare loading and failure", LoadResult.LOADING, LoadResult.FAILURE)
        assertNotEquals("Compare success and failure", LoadResult.SUCCESS, LoadResult.FAILURE)
    }
}