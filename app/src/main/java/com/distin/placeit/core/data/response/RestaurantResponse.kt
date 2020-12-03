package com.distin.placeit.core.data.response

import android.location.Location
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("place_id") val placeId: String?,
    val name: String?,
    val rating: String?,
    val formatted_phone_number: String?,
    val formatted_address: String?,
    val types: List<String>,
    val geometry: GeometryResponse?,
    val photos: List<PhotosResponse>?,
    @SerializedName("opening_hours") val openingHours: OpeningHoursResponse?
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