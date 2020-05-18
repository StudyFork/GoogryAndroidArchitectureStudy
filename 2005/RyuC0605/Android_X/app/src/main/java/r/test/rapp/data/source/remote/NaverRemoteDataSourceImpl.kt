package r.test.rapp.data.source.remote

import r.test.rapp.BuildConfig
import r.test.rapp.data.model.MovieVo
import r.test.rapp.networks.NaverApi
import r.test.rapp.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDataSourceImpl :
    NaverRemoteDataSource {
    override fun getData(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    ) {
        val api: NaverApi =
            RetrofitClient.getClient(BuildConfig.NAVER_API_URL).create(NaverApi::class.java)

        api.searchMovie(keyword).enqueue(object : Callback<MovieVo> {
            override fun onFailure(call: Call<MovieVo>, t: Throwable) {
                onFail(t);
            }

            override fun onResponse(call: Call<MovieVo>, response: Response<MovieVo>) {
                response.body()?.let { onSuccess(it) };
            }
        })
    }
}