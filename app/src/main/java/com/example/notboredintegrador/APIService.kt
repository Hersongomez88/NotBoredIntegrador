package com.example.notboredintegrador

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Makes a call to the 'activity/' endpoint.
 * Receives the number of participants (or null for random number of participants)
 * and the type of activity (or null for a random type)
 * */
interface APIService {
    @GET("activity/")
    suspend fun getActivity(
        @Query("participants") participants: Int?,
        @Query("type") type: String?
    ): Response<ActivitiesResponse>
}