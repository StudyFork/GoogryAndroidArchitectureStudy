package r.test.data.remote

import okhttp3.ResponseBody
import r.test.data.BuildConfig
import r.test.data.model.MovieVo
import r.test.data.networks.NaverApi
import r.test.data.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

internal class NaverRemoteDataSourceImpl(private val api: NaverApi) :
    NaverRemoteDataSource {
    override fun getData(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    ) {
//        val api: NaverApi =
//            RetrofitClient.getClient(BuildConfig.NAVER_API_URL).create(NaverApi::class.java)

        api.searchMovie(keyword).enqueue(object : Callback<MovieVo> {
            override fun onFailure(call: Call<MovieVo>, t: Throwable) {
                onFail(t);
            }

            override fun onResponse(call: Call<MovieVo>, response: Response<MovieVo>) {
                if(response.isSuccessful){
                    response.body()?.let { onSuccess(it) };
                }else{

                    val converter: Converter<ResponseBody, MovieVo> =
                        RetrofitClient.getClient(BuildConfig.NAVER_API_URL).responseBodyConverter(
                            MovieVo::class.java, arrayOfNulls<Annotation>(0)
                        )
                    val errorModel: MovieVo? = converter.convert(response.errorBody())
                    onFail(Exception(errorModel!!.errorMessage))
                }
            }
        })
    }
}