package wooooooak.com.studyapp.data.model.datasource.local

import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.response.movie.Movie

interface NaverLocalDataSource {
    var lastBlogTitle: String
    var lastImageTitle: String
    var lastMovieTitle: String
    var lastKinTitle: String

    suspend fun getBlogList(): List<Blog>

    suspend fun getImageList(): List<Image>

    suspend fun getMovieList(): List<Movie>

    suspend fun getKinList(): List<Kin>

    suspend fun saveBlogList(list: List<Blog>)

    suspend fun saveImageList(list: List<Image>)

    suspend fun saveMovieList(list: List<Movie>)

    suspend fun saveKinList(list: List<Kin>)

    suspend fun clearBlogList()

    suspend fun clearImageList()

    suspend fun clearMovieList()

    suspend fun clearKinList()
}