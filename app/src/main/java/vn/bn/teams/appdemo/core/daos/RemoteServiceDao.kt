package vn.bn.teams.appdemo.core.daos

import retrofit2.http.GET
import vn.bn.teams.appdemo.data.models.JobsResopnse
import vn.bn.teams.appdemo.data.models.PostsResponse


interface RemoteServiceDao {

    @GET("jobs")
    suspend fun getAllJobs(): JobsResopnse

    @GET("/posts" )
    suspend fun posts(): List<PostsResponse>

}