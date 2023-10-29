package vn.bn.teams.appdemo.data.models

data class UserResponse(
    var result: UserResult,
    var code: Int,
    var message: String
) {
}