package com.example.notboredintegrador

import com.google.gson.annotations.SerializedName

data class ActivitiesResponse(
    @SerializedName("activity") var description: String,
    @SerializedName("type") var category: String,
    @SerializedName("participants") var participants: Int,
    @SerializedName("price") var price: Double
)
