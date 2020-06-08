package r.test.rapp.main

import android.text.TextUtils
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl

class MainViewModel {
    private val repository: MovieRepository = MovieRepositoryImpl()

    val movies = ObservableArrayList<Item>()
    val keyword = ObservableField<String>()
    val toastMsg = ObservableField<String>()
    val toastRes = ObservableField<Int>()
    val isLoading = ObservableField<Boolean>()
    val showKeypad = ObservableField<Boolean>()

    fun searchData() {

        val searchQuery = keyword.get() ?: return

        if (TextUtils.isEmpty(searchQuery)) {
            toastRes.set(R.string.enter_keyword)
            return
        }
        isLoading.set(true)
        showKeypad.set(false)


        if (searchQuery != null) {
            repository.getMovieList(
                searchQuery,
                onSuccess = { vo ->
                    movies.addAll(vo.items)
                    isLoading.set(false)
                },
                onFail = { f ->
                    isLoading.set(false)
                    toastMsg.set(f.toString())
                })
        };
    }

}