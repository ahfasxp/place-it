package com.distin.placeit.core.data.response

import com.google.gson.annotations.SerializedName

data class BaseResultResponse<T>(
    @SerializedName("results") val results: List<T>
)