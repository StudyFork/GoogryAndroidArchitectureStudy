package kr.dktsudgg.androidarchitecturestudy.data.datasource.remote

import kr.dktsudgg.androidarchitecturestudy.BuildConfig
import kr.dktsudgg.androidarchitecturestudy.api.WebRequestManager
import kr.dktsudgg.androidarchitecturestudy.api.naver.NaverMovieApi
import kr.dktsudgg.androidarchitecturestudy.data.model.NaverMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverMovieDataSourceImpl : NaverMovieDataSource {

    private val NAVER_CLIENT_ID = BuildConfig.NAVER_CLIENT_ID;
    private val NAVER_CLIENT_SECRET = BuildConfig.NAVER_CLIENT_SECRET;

    override fun searchMovies(
        query: String,
        display: Int?,
        start: Int?,
        successCallback: (NaverMovieResponse?) -> Unit,
        failureCallback: (t: Throwable) -> Unit
    ) {
        WebRequestManager.getInstance(NaverMovieApi::class)
            ?.searchMovies(NAVER_CLIENT_ID, NAVER_CLIENT_SECRET, query, display, start)
            ?.enqueue(object : Callback<NaverMovieResponse> {
                override fun onResponse(
                    call: Call<NaverMovieResponse>,
                    response: Response<NaverMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        successCallback(response.body())
                    }
                }

                override fun onFailure(call: Call<NaverMovieResponse>, t: Throwable) {
                    failureCallback(t)
                }

            })
    }

}