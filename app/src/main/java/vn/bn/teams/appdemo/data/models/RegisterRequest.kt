package vn.bn.teams.appdemo.data.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("username")
    var username: String,

    @SerializedName("name")
    var name: String
)