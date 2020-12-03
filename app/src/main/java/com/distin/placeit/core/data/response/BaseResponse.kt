package com.distin.placeit.core.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("result") val results: T
)