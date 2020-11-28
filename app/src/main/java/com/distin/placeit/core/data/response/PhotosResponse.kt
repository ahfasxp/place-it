package com.distin.placeit.core.data.response

import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("photo_reference") val photoReference: String?
)