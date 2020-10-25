package com.camai.archtecherstudy.observer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.repository.MovieRepository
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

class MainViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
)
    : ViewModel()
{

    var keyword = MutableLiveData<String>()
    private val _movieList = MutableLiveData<List<Items>>()
    val movieList: LiveData<List<Items>> = _movieList
    val isVisibile = MutableLiveData<Boolean>(false)

    val searchMovie = MutableLiveData<Unit>()
    val textNull = MutableLiveData<Unit>()
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

            repository.getMovieNameSearch(name, 100, 1,
                success = {
                    //  movie list data to recycler View
                    _movieList.value = it
                    //  Progress Gone
                    isVisibile.value = false
                    keyword.value = ""
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
        repository.getRecentSearchList(namelist = {
            if (it.isEmpty()) {
                isFailed.value = Unit
            } else {
                recentList.value = it
                isSuccess.value = Unit
            }
        })
    }


    fun closeRecent() {
        closeDialog.value = Unit

    }

}