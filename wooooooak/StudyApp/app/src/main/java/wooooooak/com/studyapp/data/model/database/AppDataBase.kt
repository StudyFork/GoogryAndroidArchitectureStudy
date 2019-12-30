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
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: kotlin.run {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "word_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }

    }
}