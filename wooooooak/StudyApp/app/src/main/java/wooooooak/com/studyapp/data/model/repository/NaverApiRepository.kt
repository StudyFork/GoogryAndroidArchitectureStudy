package wooooooak.com.studyapp.data.model.repository

import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.response.movie.Movie

interface NaverApiRepository {
    var lastBlogTitle: String
    var lastImageTitle: String
    var lastMovieTitle: String
    var lastKinTitle: String
//    var lastBlogPage: Int
//    var lastImagePage: Int
//    var lastMoviePage: Int
//    var lastKinPage: Int

    suspend fun getBlogList(title: String, startIndex: Int? = 1, cached: Boolean = false): List<Blog>

    suspend fun getImageList(title: String, startIndex: Int? = 1, cached: Boolean = false): List<Image>

    suspend fun getMovieList(title: String, startIndex: Int? = 1, cached: Boolean = false): List<Movie>

    suspend fun getKinList(title: String, startIndex: Int? = 1, cached: Boolean = false): List<Kin>
}