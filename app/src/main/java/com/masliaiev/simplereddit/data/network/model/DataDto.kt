package com.masliaiev.simplereddit.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataDto(

    @SerializedName("after")
    @Expose
    val after: String?,

    @SerializedName("children")
    @Expose
    val children: List<ChildDto>
)
