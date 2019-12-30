package wooooooak.com.studyapp.data.model.repository

import wooooooak.com.studyapp.data.model.datasource.local.NaverLocalDataSource
import wooooooak.com.studyapp.data.model.datasource.remote.NaverRemoteDataSourceImpl

class NaverApiRepositoryImpl(
    private val naverLocalDataSource: NaverLocalDataSource
) : NaverApiRepository {
    private val naverRemoteDataSource = NaverRemoteDataSourceImpl()

    override var lastBlogTitle: String
        get() = naverLocalDataSource.lastBlogTitle
        set(value) {
            naverLocalDataSource.lastBlogTitle = value
        }

    override var lastImageTitle: String
        get() = naverLocalDataSource.lastImageTitle
        set(value) {
            naverLocalDataSource.lastImageTitle = value
        }

    override var lastMovieTitle: String
        get() = naverLocalDataSource.lastMovieTitle
        set(value) {
            naverLocalDataSource.lastMovieTitle = value
        }

    override var lastKinTitle: String
        get() = naverLocalDataSource.lastKinTitle
        set(value) {
            naverLocalDataSource.lastKinTitle = value
        }

    override suspend fun getBlogList(title: String, startIndex: Int?, cached: Boolean) = if (cached) {
        naverLocalDataSource.getBlogList()
    } else {
        lastBlogTitle = title
        val blogList = naverRemoteDataSource.getBlogList(lastBlogTitle, startIndex)
        startIndex?.let {
            if (it <= 1) {
                naverLocalDataSource.clearBlogList()
            }
        }
        naverLocalDataSource.saveBlogList(blogList)
        blogList
    }

    override suspend fun getImageList(title: String, startIndex: Int?, cached: Boolean) = if (cached) {
        naverLocalDataSource.getImageList()
    } else {
        lastImageTitle = title
        val imageList = naverRemoteDataSource.getImageList(lastImageTitle, startIndex)
        startIndex?.let {
            if (it <= 1) {
                naverLocalDataSource.clearImageList()
            }
        }
        naverLocalDataSource.saveImageList(imageList)
        imageList
    }

    override suspend fun getMovieList(title: String, startIndex: Int?, cached: Boolean) = if (cached) {
        naverLocalDataSource.getMovieList()
    } else {
        lastMovieTitle = title
        val movieList = naverRemoteDataSource.getMovieList(lastMovieTitle, startIndex)
        startIndex?.let {
            if (it <= 1) {
                naverLocalDataSource.clearMovieList()
            }
        }
        naverLocalDataSource.saveMovieList(movieList)
        movieList

    }

    override suspend fun getKinList(title: String, startIndex: Int?, cached: Boolean) = if (cached) {
        naverLocalDataSource.getKinList()
    } else {
        lastKinTitle = title
        val kinList = naverRemoteDataSource.getKinList(lastKinTitle, startIndex)
        startIndex?.let {
            if (it <= 1) {
                naverLocalDataSource.clearKinList()
            }
        }
        naverLocalDataSource.saveKinList(kinList)
        kinList
    }

}