package com.kou.example.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Network response class
 */
class Response {
    @JsonClass(generateAdapter = true)
    class BookResult (
        @Json(name = "data") val data: List<Book>,
        @Json(name = "total") val total: Int
    )

    @JsonClass(generateAdapter = true)
    class Book (
        @Json(name = "url") val url: String,
        @Json(name = "startDate") val startDate: String,
        @Json(name = "endDate") val endDate: String,
        @Json(name = "name") val name: String,
        @Json(name = "icon") val icon: String,
        @Json(name = "objType") val objType: String,
        @Json(name = "loginRequired") val loginRequired: Boolean
    )
}