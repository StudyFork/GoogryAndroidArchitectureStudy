package m.woong.architecturestudy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import m.woong.architecturestudy.data.repository.MovieRepository
import m.woong.architecturestudy.data.source.remote.model.MovieResponse

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    // two-way databinding
    val queryData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val queryNullorEmpty: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    private val _successResponse: MutableLiveData<List<MovieResponse.Item>> by lazy {
        MutableLiveData<List<MovieResponse.Item>>()
    }
    val successResponse: LiveData<List<MovieResponse.Item>>
        get() = _successResponse

    fun searchMovie() {
        Log.v("MVVM", "searchMovie 호출 ${queryData.value}")

        if (!queryData.value.isNullOrEmpty()) {
            getRecentMovie(queryData.value.toString())
        } else {
            queryNullorEmpty.value = true
        }
    }

    private fun getRecentMovie(query: String) {
        Log.v("MVVM", "getRecentMovie 호출 $query")
        repository.getRecentMovie(query,
            success = {
                Log.v("MVVM", "getRecentMovie success : $it")
                _successResponse.value = it.items
            }, failure = {
                Log.v(
                    "MVVM", "응답을 받아오지 못했습니다.\n" +
                            " 에러코드:$it"
                )
            })
    }
}
