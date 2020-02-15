package com.siwon.prj.view

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siwon.prj.common.model.Movie
import com.siwon.prj.repository.MovieSearchRepository

class MainViewmodel(val repository: MovieSearchRepository) : ViewModel() { // aac viewmodel 구현
    /**
     *
    fun showResult(result: ArrayList<Movie>)
    fun emptyInput()
    fun emptyResult()
    fun serviceErr(erMsg: String)
    fun hideKeyboard()

     */
//    private val repository: MovieSearchRepository = MovieSearchRepositoryImpl()

    // 검색 결과리스트
    val searchResult = MutableLiveData<ArrayList<Movie>>()
    // 검색결과 유/무
    val isResultEmpty: ObservableField<Boolean> = ObservableField()
    // 오류 및 안내 토스트메시지
    val toastMsg = MutableLiveData<String>()
    // input값
    val userInput = MutableLiveData<String>()
    // 키보드visibility
    val isKeyboardVisible = MutableLiveData<Boolean>()

    fun searchMovie() {
        val input = userInput.value
        if (input.isNullOrBlank()) {
            // TODO: 입력값 없는경우 처리 --> toastMsg로 '검색어를 입력해 주세요!'
            toastMsg.value = "검색어를 입력해 주세요!"
        } else {
            repository.searchMovies(input,
                success = { result ->
                    result.let {
                        if (it.isNotEmpty()) {
                            searchResult.value = it
                            this.isResultEmpty.set(false)
                        } else this.isResultEmpty.set(true)
                        isKeyboardVisible.value = false
                        // TODO: 검색결과 없는경우 처리 --> 검색결과 없음 이미지 visible
                    }
                },
                fail = {
                    // TODO: 검실패한 경우 처리
                    toastMsg.value = "검색오류\n${it.message}\n입니다!"
                })
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}