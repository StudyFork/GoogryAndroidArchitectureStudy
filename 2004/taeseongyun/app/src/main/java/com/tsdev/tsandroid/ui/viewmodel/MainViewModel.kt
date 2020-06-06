package com.tsdev.tsandroid.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.ext.SingleEventLiveData
import com.tsdev.tsandroid.ext.SingleMutableLiveData
import com.tsdev.tsandroid.provider.ResourceProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val movieRepository: NaverReopsitory,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    companion object {
        const val NON_QUERY = "N/A"
    }

    private val _isLoading = SingleMutableLiveData<Boolean>()
    val isLoading: SingleEventLiveData<Boolean>
        get() = _isLoading

    private val _movieList = MutableLiveData<List<Item>>()
    val movieList: LiveData<List<Item>>
        get() = _movieList

    val query = MutableLiveData<String>()

    private val _oldMovieList = MutableLiveData<() -> Unit>()
    val oldMovieList: LiveData<() -> Unit>
        get() = _oldMovieList

    lateinit var onClearList: () -> Unit

    fun searchMovie(hideKeyBoard: () -> Unit) {
        compositeDisposable.add(
            movieRepository.getMovieList(query.value ?: NON_QUERY)
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    it.printStackTrace()
                    resourceProvider.getResultErrorString(R.string.occur_error_toast)
                    emptyList()
                }.observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isLoading.event = true
                    hideKeyBoard()
                }
                .doAfterTerminate { _isLoading.event = false }
                .subscribe { items: List<Item>, _ ->
                    items.takeIf { list -> list.isNotEmpty() }
                        ?.run {
                            if (_movieList.value?.containsAll(this) == true) {
                                return@subscribe
                            } else {
                                _oldMovieList.value = onClearList
                                _movieList.value = this
                            }
                        }
                        ?: run {
                            _oldMovieList.value = onClearList
                            _movieList.value = null
                            showToastMessage(resourceProvider.getResultErrorString(R.string.non_search_result))
                        }
                }
        )
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(resourceProvider.getContext, message, Toast.LENGTH_LONG).show()
    }
}