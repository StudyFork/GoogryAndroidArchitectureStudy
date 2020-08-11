package mi.song.class12android.module

import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl
import mi.song.class12android.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieSearchModule = module {
    single<SearchMovieRepository> { SearchMovieRepositoryImpl(get()) }
    viewModel { MovieViewModel(get()) }
}