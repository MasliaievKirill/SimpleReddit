package com.masliaiev.simplereddit.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChildDto(

    @SerializedName("data")
    @Expose
    val postDto: PostDto
)
