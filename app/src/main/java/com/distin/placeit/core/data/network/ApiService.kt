package com.distin.placeit.core.data.network

import com.distin.placeit.BuildConfig
import com.distin.placeit.core.data.response.BaseResultResponse
import com.distin.placeit.core.data.response.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("place/nearbysearch/json")
    suspend fun getRestaurant(
        @Query("location") location: String?,
        @Query("radius") radius: Int? = 1000,
        @Query("type") type: String? = "restaurant",
        @Query("key") key: String? = BuildConfig.API_KEY
    ): BaseResultResponse<List<RestaurantResponse>>

    @GET("place/details/json")
    suspend fun getDetailRestaurant(
        @Query("place_id") placeId: String?,
        @Query("key") key: String? = BuildConfig.API_KEY
    ): BaseResultResponse<RestaurantResponse>
}