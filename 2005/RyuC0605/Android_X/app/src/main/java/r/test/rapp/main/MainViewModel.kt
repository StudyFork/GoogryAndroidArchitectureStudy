package r.test.rapp.main

import android.content.res.Resources
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl

class MainViewModel(private val res: Resources) : ViewModel() {
    private val repository: MovieRepository = MovieRepositoryImpl()

    val movies = MutableLiveData<List<Item>>()
    val keyword = MutableLiveData<String>()
    val toastMsg = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val showKeypad = MutableLiveData<Boolean>()

    fun searchData() {

        val searchQuery = keyword.value

        if (TextUtils.isEmpty(searchQuery)) {
            toastMsg.value = res.getString(R.string.enter_keyword)
            return
        }
        isLoading.value = true
        showKeypad.value = false


        repository.getMovieList(
            searchQuery!!,
            onSuccess = { vo ->
                movies.value = vo.items
                isLoading.value = false
            },
            onFail = { f ->
                isLoading.value = false
                toastMsg.value = f.toString()
            })
    }

}