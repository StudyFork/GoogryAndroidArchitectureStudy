package com.siwon.prj.view

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.siwon.prj.common.model.Movie
import com.siwon.prj.repository.MovieSearchRepository
import com.siwon.prj.repository.MovieSearchRepositoryImpl

class MainViewmodel {
    /**
     *
    fun showResult(result: ArrayList<Movie>)
    fun emptyInput()
    fun emptyResult()
    fun serviceErr(erMsg: String)
    fun hideKeyboard()

     */
    private val repository: MovieSearchRepository = MovieSearchRepositoryImpl()

    // 검색 결과리스트
    val searchResult: ObservableArrayList<Movie> = ObservableArrayList()
    // 검색결과 유/무
    val isResultEmpty: ObservableField<Boolean> = ObservableField()
    // 오류 및 안내 토스트메시지
    val toastMsg: ObservableField<String> = ObservableField()
    // input값
    var userInput: ObservableField<String> = ObservableField()
    // 키보드visibility
    val isKeyboardVisible: ObservableField<Boolean> = ObservableField(true)

    fun searchMovie() {
        val input = userInput.get()
        if (input.isNullOrBlank()) {
            // TODO: 입력값 없는경우 처리 --> toastMsg로 '검색어를 입력해 주세요!'
            toastMsg.set("검색어를 입력해 주세요!")
        } else {
            repository.searchMovies(input,
                success = { result ->
                    result.let {
                        if (it.isNotEmpty()) {
//                            searchResult.clone()
                            searchResult.clear()
                            searchResult.addAll(it)
                            isResultEmpty.set(false)
                        } else {
                            // TODO: 검색결과 없는경우 처리 --> 검색결과 없음 이미지 visible
                            isResultEmpty.set(true)
                        }
                        isKeyboardVisible.set(false)
                    }
                },
                fail = {
                    // TODO: 검실패한 경우 처리
                    toastMsg.set("검색오류\n${it.message}\n입니다!")
                })
        }
    }
}