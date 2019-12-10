package wooooooak.com.studyapp.data.model.datasource.remote

import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.response.movie.Movie

interface NaverRemoteDataSource {
    suspend fun getBlogList(title: String, startIndex: Int? = 1): List<Blog>

    suspend fun getImageList(title: String, startIndex: Int? = 1): List<Image>

    suspend fun getMovieList(title: String, startIndex: Int? = 1): List<Movie>

    suspend fun getKinList(title: String, startIndex: Int? = 1): List<Kin>
}