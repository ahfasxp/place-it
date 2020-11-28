package com.distin.placeit.core.data.response

import android.location.Location
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("place_id") val placeId: String?,
    val name: String?, val rating: String?, val types: List<String>,
    val geometry: GeometryResponse?
) {
    fun getDistance(): Int {
        val currentLocation = Location("").apply {
            latitude = geometry?.location?.lat ?: 0.0
            longitude = geometry?.location?.lng ?: 0.0
        }

        val placeLocation = Location("").apply {
            latitude = geometry?.viewport?.northeast?.lat ?: 0.0
            longitude = geometry?.viewport?.northeast?.lng ?: 0.0
        }
        return currentLocation.distanceTo(placeLocation).toInt()
    }
}