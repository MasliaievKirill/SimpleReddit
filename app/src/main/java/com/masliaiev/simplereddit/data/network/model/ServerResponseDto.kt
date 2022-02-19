package com.masliaiev.simplereddit.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerResponseDto(

    @SerializedName("data")
    @Expose
    val dataDto: DataDto
)
