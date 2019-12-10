package wooooooak.com.studyapp.data.model.datasource.remote

import wooooooak.com.studyapp.common.constants.DISPLAY_LIST_COUNT
import wooooooak.com.studyapp.naverApi

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    override suspend fun getBlogList(title: String, startIndex: Int?) =
        naverApi.getBlogs(title, DISPLAY_LIST_COUNT, startIndex).items

    override suspend fun getImageList(title: String, startIndex: Int?) =
        naverApi.getImages(title, DISPLAY_LIST_COUNT, startIndex).items

    override suspend fun getMovieList(title: String, startIndex: Int?) =
        naverApi.getMovies(title, DISPLAY_LIST_COUNT, startIndex).items

    override suspend fun getKinList(title: String, startIndex: Int?) =
        naverApi.getKins(title, DISPLAY_LIST_COUNT, startIndex).items

}