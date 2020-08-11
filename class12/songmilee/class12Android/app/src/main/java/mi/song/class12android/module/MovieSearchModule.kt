package mi.song.class12android.module

import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl
import org.koin.dsl.module

val movieSearchModule = module {
    single<SearchMovieRepository> { SearchMovieRepositoryImpl(get()) }
    single<SearchMovieRepositoryImpl> { SearchMovieRepositoryImpl(get()) }
}