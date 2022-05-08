package com.kou.example.repository

import com.kou.example.entities.DataResult
import com.kou.example.entities.Response
import com.kou.example.repository.api.BookAPI

/**
 * Repository class used on the project
 */
class MyRepository(private val api: BookAPI) {
    suspend fun getUpcomingBookList(): DataResult<List<Response.Book>> =
        try {
            val result = api.getUpcomingBookData()
            DataResult.SUCCESS(result.data)
        } catch (e: Exception) {
            DataResult.FAILURE(e)
        }
}