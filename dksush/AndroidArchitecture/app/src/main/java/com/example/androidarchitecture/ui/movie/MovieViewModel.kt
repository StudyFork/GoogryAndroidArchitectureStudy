package com.example.androidarchitecture.ui.movie

import android.text.TextUtils
import androidx.databinding.ObservableField
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.MovieData

class MovieViewModel(private val naverRepositroy: NaverRepoInterface) {

    var inputKeyword = naverRepositroy.getMoiveKeyword()
    val blankInputText = ObservableField<Unit>()
    val renderItems = ObservableField<List<MovieData>>()
    val errorToast = ObservableField<Throwable>()
    val isListEmpty = ObservableField<Boolean>(true)


    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        inputKeyword = s.toString()
    }


    suspend fun requestSearchHist() {
        naverRepositroy.getMovieHist().let {
            if (it.isNotEmpty()) {
                isListEmpty.set(it.isEmpty())
                renderItems.set(it)

            }
        }

    }


    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.set(Unit)
        } else {
            naverRepositroy.saveMovieKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getMovie(inputKeyword, 1, 10,
                success = {
                    isListEmpty.set(false)
                    renderItems.set(it)
                }, fail = {
                    errorToast.set(Throwable())
                })
        }
    }
}