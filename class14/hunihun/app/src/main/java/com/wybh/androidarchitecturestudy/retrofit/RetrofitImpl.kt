package com.wybh.androidarchitecturestudy.retrofit

import com.wybh.androidarchitecturestudy.model.ResponseCinemaData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitImpl {

    @Headers("X-Naver-Client-Id: VsqhmqtGTmcUWFZ_Cv2I", "X-Naver-Client-Secret: bfUieu0BGT")
    @GET("movie.json")
    fun getCinemaData(@Query("query") query: String?): Observable<ResponseCinemaData>
}