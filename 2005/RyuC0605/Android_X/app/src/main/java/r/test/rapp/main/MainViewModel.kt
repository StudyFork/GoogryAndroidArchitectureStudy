package r.test.rapp.main

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.data.repository.MovieRepository

class MainViewModel(private val res: Application, private val repository: MovieRepository) : ViewModel() {
//    private val repository: MovieRepository = MovieRepositoryImpl(NaverRemoteDataSourceImpl())

    private val _movies = MutableLiveData<List<Item>>()
    private val _keyword = MutableLiveData<String>()
    private val _toastMsg = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _hideKeypad = MutableLiveData<Unit>()

    val movies: LiveData<List<Item>> = _movies
    val keyword: MutableLiveData<String> = _keyword
    val toastMsg: LiveData<String> = _toastMsg
    val isLoading: LiveData<Boolean> = _isLoading
    val hideKeypad: LiveData<Unit> = _hideKeypad

    fun searchData() {

        val searchQuery = keyword.value

        if (TextUtils.isEmpty(searchQuery)) {
            _toastMsg.value = res.getString(R.string.enter_keyword)
            return
        }
        _isLoading.value = true
        _hideKeypad.value = Unit


        repository.getMovieList(
            searchQuery!!,
            onSuccess = { vo ->
                _movies.value = vo.items
                _isLoading.value = false
            },
            onFail = { f ->
                _isLoading.value = false
                _toastMsg.value = f.toString()
            })
    }

}