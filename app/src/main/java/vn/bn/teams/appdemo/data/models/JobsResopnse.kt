package vn.bn.teams.appdemo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "JobsResopnse")
data class JobsResopnse(

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int? = null)