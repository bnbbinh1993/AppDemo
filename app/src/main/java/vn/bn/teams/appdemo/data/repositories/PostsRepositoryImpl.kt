package vn.bn.teams.appdemo.data.repositories

import vn.bn.teams.appdemo.data.models.JobsResopnse
import vn.bn.teams.appdemo.data.models.PostsResponse
import vn.bn.teams.appdemo.core.daos.RemoteServiceDao

/**
 * Created by dev.mahmoud_ashraf on 12/11/2019.
 */
class PostsRepositoryImpl  constructor(private val remoteJobsDao: RemoteServiceDao) :
    PostsRepository {
    override suspend fun getPosts(): List<PostsResponse> {
           return remoteJobsDao.posts()
    }

    override suspend fun getRemoteJobs(): JobsResopnse {
        val jobs = remoteJobsDao.getAllJobs()
       // localJobsDao.insert(jobs.jobs)
        return jobs
    }

    override suspend fun getLocalJobs(): JobsResopnse {
        return JobsResopnse()
    }
}