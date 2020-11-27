package com.distin.placeit.core.data.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("place_id") val placeId: String?,
    val name: String?
)