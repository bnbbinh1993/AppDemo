package vn.bn.teams.appdemo.data.repositories

import vn.bn.teams.appdemo.data.models.JobsResopnse
import vn.bn.teams.appdemo.data.models.PostsResponse


/**
 * Created by dev.mahmoud_ashraf on 12/11/2019.
 */
interface PostsRepository {
    suspend fun getRemoteJobs(): JobsResopnse

    suspend fun getLocalJobs(): JobsResopnse

    suspend fun getPosts(): List<PostsResponse>
}