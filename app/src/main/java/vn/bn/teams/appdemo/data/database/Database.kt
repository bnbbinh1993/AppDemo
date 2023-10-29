package vn.bn.teams.appdemo.data.database
import androidx.room.Database
import androidx.room.RoomDatabase
import vn.bn.teams.appdemo.data.models.JobsResopnse


@Database(entities = [JobsResopnse::class/*, Issue::class*/], version = 1)
abstract class Database : RoomDatabase() {

  //  abstract fun localJobsDao(): LocalJobsDao

    //abstract fun localIssuesDao(): LocalIssuesDao
}