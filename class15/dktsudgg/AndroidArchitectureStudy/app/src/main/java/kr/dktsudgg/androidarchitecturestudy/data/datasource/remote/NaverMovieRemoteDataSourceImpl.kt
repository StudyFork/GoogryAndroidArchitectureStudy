package kr.dktsudgg.androidarchitecturestudy.data.datasource.remote

import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.dktsudgg.androidarchitecturestudy.BuildConfig
import kr.dktsudgg.androidarchitecturestudy.api.NetworkRequestManager
import kr.dktsudgg.androidarchitecturestudy.api.StudyHttpRequest
import kr.dktsudgg.androidarchitecturestudy.api.naver.NaverMovieApi
import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class NaverMovieRemoteDataSourceImpl @Inject constructor(
    @StudyHttpRequest private val networkRequestManager: NetworkRequestManager
) : NaverMovieRemoteDataSource {

    private val NAVER_CLIENT_ID = BuildConfig.NAVER_CLIENT_ID;
    private val NAVER_CLIENT_SECRET = BuildConfig.NAVER_CLIENT_SECRET;

    override fun searchMovies(
        query: String,
        display: Int?,
        start: Int?,
        successCallback: (NaverMovieResponse) -> Unit,
        failureCallback: (t: Throwable) -> Unit
    ) {
        networkRequestManager.getClient(NaverMovieApi::class)
            ?.searchMovies(NAVER_CLIENT_ID, NAVER_CLIENT_SECRET, query, display, start)
            ?.enqueue(object : Callback<NaverMovieResponse> {
                override fun onResponse(
                    call: Call<NaverMovieResponse>,
                    response: Response<NaverMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            successCallback(it)
                        } ?: {  // 널인 경우
                            Log.i(
                                "NaverMovieDataSourceImpl - searchMovies",
                                "response.body() is null.."
                            )
                        }()
                    }
                }

                override fun onFailure(call: Call<NaverMovieResponse>, t: Throwable) {
                    failureCallback(t)
                }

            })
    }

}

@InstallIn(ApplicationComponent::class)
@Module
abstract class NaverMovieRemoteDataModule {

    @Binds
    @Singleton
    abstract fun bindNaverMovieRemoteData(naverMovieRemoteDataSourceImpl: NaverMovieRemoteDataSourceImpl): NaverMovieRemoteDataSource

}