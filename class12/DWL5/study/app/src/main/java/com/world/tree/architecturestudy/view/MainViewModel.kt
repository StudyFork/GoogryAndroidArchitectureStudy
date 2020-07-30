package com.world.tree.architecturestudy.view

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.world.tree.architecturestudy.model.Movie
import com.world.tree.architecturestudy.model.repository.remote.NaverRepository
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(private val repository: NaverRepository) {

    val movieList = ObservableArrayList<Movie.Item>()
    val title = ObservableField<String>()
    val toastMsg = ObservableField<String>()

    fun searchMovie(q: String) {
        if (q.isBlank()) {
            toastMsg.set("검색어를 입력 해 주세요")
            toastMsg.set("")
        }

        repository.getMovies(q, success = {
            movieList.clear()
            movieList.addAll(it)
        }, error = {
            it.message?.let { message ->
                toastMsg.set(message)
                toastMsg.set("")
            }
        })
    }
}