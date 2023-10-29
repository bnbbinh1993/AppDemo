package vn.bn.teams.appdemo.data.models


data class BigLisResult(
    var data: ArrayList<DataBigList>,
    var output: DataBigList,
    var rowsAffected: ArrayList<Int>
) {
}