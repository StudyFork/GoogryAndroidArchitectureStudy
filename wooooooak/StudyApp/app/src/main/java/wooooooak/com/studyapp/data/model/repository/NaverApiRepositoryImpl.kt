package wooooooak.com.studyapp.data.model.repository

import wooooooak.com.studyapp.data.model.datasource.remote.NaverRemoteDataSourceImpl

class NaverApiRepositoryImpl : NaverApiRepository {
    private val naverRemoteDataSource = NaverRemoteDataSourceImpl()

    override suspend fun getBlogList(title: String, startIndex: Int?) =
        naverRemoteDataSource.getBlogList(title, startIndex)

    override suspend fun getImageList(title: String, startIndex: Int?) =
        naverRemoteDataSource.getImageList(title, startIndex)

    override suspend fun getMovieList(title: String, startIndex: Int?) =
        naverRemoteDataSource.getMovieList(title, startIndex)

    override suspend fun getKinList(title: String, startIndex: Int?) =
        naverRemoteDataSource.getKinList(title, startIndex)

}