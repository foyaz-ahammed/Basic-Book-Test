package com.kou.example.repository.api

import com.kou.example.entities.Response
import retrofit2.http.GET

/**
 * API interface used for the network call
 */
interface BookAPI {
    @GET("service/v2/upcomingGuides/")
    suspend fun getUpcomingBookData(): Response.BookResult
}