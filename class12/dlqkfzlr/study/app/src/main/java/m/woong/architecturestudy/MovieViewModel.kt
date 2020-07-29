package m.woong.architecturestudy

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import m.woong.architecturestudy.data.repository.MovieRepository
import m.woong.architecturestudy.ui.adapter.MovieAdapter

class MovieViewModel(
    private val adapter: MovieAdapter,
    private val repository: MovieRepository
) : ViewModel() {
    val queryData = ObservableField<String>()
    val movieAdapter = adapter

    fun searchMovie() {
        if (queryData.get() == null) {
            Log.v("MovieViewModel", "searchMovie 에러 empty Search")      // TODO: Toast메세지로 교체
        } else {
            getRecentMovie(queryData.get().toString())
        }
    }

    private fun getRecentMovie(query: String) {
        Log.v("MovieViewModel", "getRecentMovie 호출 $query")
        repository.getRecentMovie(query,
            success = {
                Log.v("MovieViewModel", "getRecentMovie success : $it")
                adapter.resetData(it.items)
            }, failure = {
                Log.v(
                    "MovieViewModel", "응답을 받아오지 못했습니다.\n" +
                            " 에러코드:$it"
                )   // TODO: Toast메세지로 교체
            })
    }
}