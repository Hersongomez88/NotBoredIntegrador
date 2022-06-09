package com.example.notboredintegrador

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("activity/")
    suspend fun getActivity(
        @Query("participants") participants: Int?,
        @Query("type") type: String?
    ): Response<ActivitiesResponse>
}