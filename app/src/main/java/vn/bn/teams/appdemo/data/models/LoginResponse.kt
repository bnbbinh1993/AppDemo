package vn.bn.teams.appdemo.data.models

data class LoginResponse (
    var result: ResultLogin,
    var code: Int,
    var message: String
)