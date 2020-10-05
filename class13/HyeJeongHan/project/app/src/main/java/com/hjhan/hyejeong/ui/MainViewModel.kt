package com.hjhan.hyejeong.ui

import androidx.databinding.ObservableField
import com.hjhan.hyejeong.data.model.Item
import com.hjhan.hyejeong.data.repository.NaverRepository

class MainViewModel(private val repositoryImpl: NaverRepository) {

    val query = ObservableField<String>()
    val movieList = ObservableField<List<Item>>()
    val onFailedEvent = ObservableField<Unit>()
    val onEmptyQuery = ObservableField<Unit>()

    fun setMovieList() {
        if (query.get().isNullOrBlank()) {
            // 검색어 없을때 처리
            onEmptyQuery.notifyChange()

        } else {
            repositoryImpl.getSearchMovies(
                query = query.get()!!,
                success = {
                    it.items.run {
                        //항상 리스트 통으로 넘겨줘야함.
                        movieList.set(it.items)
                    }
                },
                failed = {
                    onFailedEvent.notifyChange()
                })
        }

    }

}