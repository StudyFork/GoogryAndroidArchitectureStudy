package wooooooak.com.studyapp.data.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import wooooooak.com.studyapp.data.model.database.dao.BlogDao
import wooooooak.com.studyapp.data.model.database.dao.ImageDao
import wooooooak.com.studyapp.data.model.database.dao.KinDao
import wooooooak.com.studyapp.data.model.database.dao.MovieDao
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.response.movie.Movie

@Database(
    entities = [Blog::class, Image::class, Kin::class, Movie::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun blogDao(): BlogDao

    abstract fun imageDao(): ImageDao

    abstract fun kinDao(): KinDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "study_app.db"
        ).build()
    }
}