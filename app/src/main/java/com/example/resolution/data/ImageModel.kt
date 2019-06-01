package com.example.resolution.data

import com.google.gson.annotations.SerializedName

data class ImageModel (
    @SerializedName("user_id")
    var userId: String,

    @SerializedName("original_image")
    var originalImage: String,

    @SerializedName("super_image")
    var superImage: String


)