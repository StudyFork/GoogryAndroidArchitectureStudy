package com.camai.archtecherstudy.observer

import androidx.databinding.ObservableField
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.repository.MovieRepository
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl

class MainViewModel() {

    var keyword = ObservableField<String>()
    val movieList = ObservableField<List<Items>>()
    val isVisibile = ObservableField<Boolean>(false)

    val searchMovie = ObservableField<Unit>()
    val textNull = ObservableField<Unit>()
    val failedSearch = ObservableField<Unit>()
    val openDialog = ObservableField<Unit>()
    private var name : String = ""

    fun onClickSearch() {
        isVisibile.set(true)

        name = keyword.get().toString()

        searchMovie.notifyChange()

        if(name.isNullOrBlank()){
            textNull.notifyChange()
        }
        else{
            MovieRepositoryImpl.getMovieNameSearch(name, 100, 1,
                success = {
                    //  movie list data to recycler View
                    movieList.set(it)
                    //  Progress Gone
                    isVisibile.set(false)
                    //  EditText Clear
                    keyword.set("")

                },
                failed = {
                    //  Progress Gone
                    isVisibile.set(false)
                    //  EditText Clear
                    //  EditText Clear
                    keyword.set("")
                    //  Toast Message
                    failedSearch.notifyChange()

                })
        }

    }

    fun openRecent(){
        openDialog.notifyChange()
    }



}