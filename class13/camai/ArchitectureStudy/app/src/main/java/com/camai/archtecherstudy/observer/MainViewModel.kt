package com.camai.archtecherstudy.observer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

class MainViewModel : ViewModel() {

    var keyword = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Items>>()
    val isVisibile = MutableLiveData<Boolean>(false)

    val searchMovie = MutableLiveData<Unit>()
    val textNull = MutableLiveData<Unit>()
    val successSearch = MutableLiveData<Unit>()
    val failedSearch = MutableLiveData<Unit>()
    val openDialog = MutableLiveData<Unit>()

    val recentList = MutableLiveData<List<RecentSearchName>>()

    val closeDialog = MutableLiveData<Unit>()
    val isSuccess = MutableLiveData<Unit>()
    val isFailed = MutableLiveData<Unit>()


    fun onClickSearch() {
        isVisibile.value = true

        searchMovie.value = Unit

        if (keyword.value.isNullOrEmpty()) {
            textNull.value = Unit
            isVisibile.value = false
        } else {
            val name: String = keyword.value ?: return

            MovieRepositoryImpl.getMovieNameSearch(name, 100, 1,
                success = {
                    //  movie list data to recycler View
                    movieList.value = it
                    //  Progress Gone
                    isVisibile.value = false
                    keyword.value = ""
                    successSearch.value = Unit
                },
                failed = {
                    //  Progress Gone
                    isVisibile.value = false
                    //  EditText Clear
                    keyword.value = ""
                    //  Toast Message
                    failedSearch.value = Unit

                })
        }

    }

    fun openRecent() {
        openDialog.value = Unit
    }

    fun setRecentData() {
        MovieRepositoryImpl.getRecentSearchList(namelist = {
            if (it.isEmpty()) {
                isFailed.value = Unit
            } else {
                recentList.value = it
                isSuccess.value = Unit
            }
        })
    }

    fun clickName() {
        isSuccess.value = Unit

    }

    fun closeRecent() {
        closeDialog.value = Unit

    }

}