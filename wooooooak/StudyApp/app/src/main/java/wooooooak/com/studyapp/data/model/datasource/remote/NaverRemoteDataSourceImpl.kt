package wooooooak.com.studyapp.data.model.datasource.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wooooooak.com.studyapp.common.constants.DISPLAY_LIST_COUNT
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.response.movie.Movie
import wooooooak.com.studyapp.naverApi

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    override suspend fun getBlogList(title: String, startIndex: Int?) = withContext(Dispatchers.IO) {
        naverApi.getBlogs(title, DISPLAY_LIST_COUNT, startIndex).items
    }

    override suspend fun getImageList(title: String, startIndex: Int?) = withContext(Dispatchers.IO) {
        naverApi.getImages(title, DISPLAY_LIST_COUNT, startIndex).items
    }

    override suspend fun getMovieList(title: String, startIndex: Int?) = withContext(Dispatchers.IO) {
        naverApi.getMovies(title, DISPLAY_LIST_COUNT, startIndex).items
    }

    override suspend fun getKinList(title: String, startIndex: Int?) = withContext(Dispatchers.IO) {
        naverApi.getKins(title, DISPLAY_LIST_COUNT, startIndex).items
    }

}