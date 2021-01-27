package com.appCraft.data.network.model

import com.google.gson.annotations.SerializedName


data class FirebaseTokenPairModel(
    @SerializedName("oldToken") val oldToken: String,
    @SerializedName("newToken") val newToken: String
)