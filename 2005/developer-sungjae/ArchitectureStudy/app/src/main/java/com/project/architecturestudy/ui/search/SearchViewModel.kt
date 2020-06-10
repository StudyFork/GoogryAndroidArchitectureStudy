package com.project.architecturestudy.ui.search

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.project.architecturestudy.R
import com.project.architecturestudy.components.Constants.customTAG
import com.project.architecturestudy.components.Strings
import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.repository.NaverMovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val repository: NaverMovieRepository) {

    val searchWord = ObservableField<String>()
    val movieData = ObservableField<List<MovieItem>>()
    val showToast = ObservableField<String>()
    val tvResultVisible = ObservableField(View.GONE)

    fun getMovieListFromLocal() {
        repository.getCashedMovieList(
            onSuccess = {
                Log.d(customTAG, "getLocalData:$it")
                movieData.set(it.toList())
                showToast.set(Strings.get(R.string.get_local_data_success))
            },
            onFailure = {
                Log.d(customTAG, "Throwable:$it")
                showToast.set(Strings.get(R.string.get_local_data_failure))
            })
    }

    fun getMovieListFromRemote(searchWord: String) {
        if (searchWord.isEmpty()) {
            showToast.set(Strings.get(R.string.please_write))
            return
        }
        this.searchWord.set(searchWord)

        tvResultVisible.set(View.VISIBLE)

        repository.getMovieList(searchWord,
            onGetRemoteData = { single ->
                single.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            Log.d(customTAG, "getRemoteData:$it")
                            movieData.set(it.items)
                            showToast.set(Strings.get(R.string.get_data_success))
                        }, { t ->
                            showToast.set(Strings.get(R.string.get_data_failure))
                            Log.d(customTAG, t.toString())
                        })
            })

    }

    fun remoteDispose() {
        repository.dispose()
    }

    fun invokeTextChanged() {
        tvResultVisible.set(View.GONE)
    }
}