package vn.bn.teams.appdemo.data.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("password")
    var password: String,

    @SerializedName("username")
    var username: String
)
