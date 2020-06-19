package r.test.rapp.main

import android.content.res.Resources
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl

class MainViewModel(private val res: Resources) : ViewModel() {
    private val repository: MovieRepository = MovieRepositoryImpl()

    private val _movies = MutableLiveData<List<Item>>()
    private val _keyword = MutableLiveData<String>()
    private val _toastMsg = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _showKeypad = MutableLiveData<Boolean>()

    val movies: LiveData<List<Item>> = _movies
    val keyword: LiveData<String> = _keyword
    val toastMsg: LiveData<String> = _toastMsg
    val isLoading: LiveData<Boolean> = _isLoading
    val showKeypad: LiveData<Boolean> = _showKeypad

    fun searchData() {

        val searchQuery = keyword.value

        if (TextUtils.isEmpty(searchQuery)) {
            _toastMsg.value = res.getString(R.string.enter_keyword)
            return
        }
        _isLoading.value = true
        _showKeypad.value = false


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