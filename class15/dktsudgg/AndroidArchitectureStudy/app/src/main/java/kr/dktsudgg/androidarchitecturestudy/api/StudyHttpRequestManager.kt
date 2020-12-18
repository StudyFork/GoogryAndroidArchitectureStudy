package kr.dktsudgg.androidarchitecturestudy.api

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kr.dktsudgg.androidarchitecturestudy.api.naver.NaverMovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

class StudyHttpRequestManager @Inject constructor() : NetworkRequestManager {

    override fun <T : Any> getClient(serviceInterface: KClass<T>): T? {
        val retrofit = Retrofit.Builder()

        when (serviceInterface) {
            NaverMovieApi::class -> {   // 네이버 영화 API통신 객체 생성
                val retrofitBuilder = retrofit.baseUrl(NAVER_OPEN_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofitBuilder.create(serviceInterface.java)
            }
            else -> {   // 정의되지 않은 API로 요청 시..
                throw IllegalArgumentException("잘못된 API 객체 요청..")
            }
        }
    }

    companion object {
        private const val NAVER_OPEN_API_URL = "https://openapi.naver.com/"
    }
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class WebRequestManagerModule {

    @StudyHttpRequest
    @Binds
    @Singleton
    abstract fun bindWebRequestManager(studyHttpRequestManager: StudyHttpRequestManager): NetworkRequestManager

}