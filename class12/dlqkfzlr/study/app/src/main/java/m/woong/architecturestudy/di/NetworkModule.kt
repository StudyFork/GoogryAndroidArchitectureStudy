package m.woong.architecturestudy.di

import m.woong.architecturestudy.data.source.remote.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideMovieApi() }
}


fun provideMovieApi(): MovieApi {
    return Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)
}