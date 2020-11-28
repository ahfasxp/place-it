package com.distin.placeit.core.data.response

import com.google.gson.annotations.SerializedName

data class OpeningHoursResponse(
    @SerializedName("open_now") val openNow: Boolean
) {
    fun isOpen(): String {
        return if (openNow) "Buka" else "Tutup"
    }
}
