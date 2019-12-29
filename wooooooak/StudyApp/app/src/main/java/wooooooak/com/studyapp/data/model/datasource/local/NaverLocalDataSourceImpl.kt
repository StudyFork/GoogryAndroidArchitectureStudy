package wooooooak.com.studyapp.data.model.datasource.local

import wooooooak.com.studyapp.data.model.database.AppDataBase
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.response.movie.Movie
import wooooooak.com.studyapp.data.model.sharedpreference.SharedPreferneceManager

class NaverLocalDataSourceImpl(
    private val preferenceManager: SharedPreferneceManager,
    dataBase: AppDataBase
) : NaverLocalDataSource {
    private val blogDao = dataBase.blogDao()
    private val imageDao = dataBase.imageDao()
    private val movieDao = dataBase.movieDao()
    private val kinDao = dataBase.kinDao()

    override var lastBlogTitle: String
        get() = preferenceManager.blogSearchTitle
        set(value) {
            preferenceManager.blogSearchTitle = value
        }

    override var lastImageTitle: String
        get() = preferenceManager.imageSearchTitle
        set(value) {
            preferenceManager.imageSearchTitle = value
        }

    override var lastMovieTitle: String
        get() = preferenceManager.movieSearchTitle
        set(value) {
            preferenceManager.movieSearchTitle = value
        }

    override var lastKinTitle: String
        get() = preferenceManager.kinSearchTitle
        set(value) {
            preferenceManager.kinSearchTitle = value
        }

//    override var lastBlogPage: Int
//        get() = preferneceManager.lastBlogPage
//        set(value) {
//            preferneceManager.lastBlogPage = value
//        }
//
//    override var lastImagePage: Int
//        get() = preferneceManager.lastImagePage
//        set(value) {
//            preferneceManager.lastImagePage = value
//        }
//
//    override var lastMoviePage: Int
//        get() = preferneceManager.lastMoviePage
//        set(value) {
//            preferneceManager.lastMoviePage = value
//        }
//
//    override var lastKinPage: Int
//        get() = preferneceManager.lastKinPage
//        set(value) {
//            preferneceManager.lastKinPage = value
//        }


    override suspend fun getBlogList() = blogDao.getAll()

    override suspend fun getImageList() = imageDao.getAll()

    override suspend fun getMovieList() = movieDao.getAll()

    override suspend fun getKinList() = kinDao.getAll()

    override suspend fun saveMovieList(list: List<Movie>) {
        movieDao.insertAll(list)
    }

    override suspend fun saveKinList(list: List<Kin>) {
        kinDao.insertAll(list)
    }

    override suspend fun saveBlogList(list: List<Blog>) {
        blogDao.insertAll(list)
    }

    override suspend fun saveImageList(list: List<Image>) {
        imageDao.insertAll(list)
    }

    override suspend fun clearBlogList() {
        blogDao.clearAll()
    }

    override suspend fun clearImageList() {
        imageDao.clearAll()
    }

    override suspend fun clearMovieList() {
        movieDao.clearAll()
    }

    override suspend fun clearKinList() {
        kinDao.clearAll()
    }
}