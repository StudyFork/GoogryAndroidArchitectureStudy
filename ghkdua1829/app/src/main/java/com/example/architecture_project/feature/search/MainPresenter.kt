package com.example.architecture_project.feature.search

import android.util.Log
import com.example.architecture_project.data.repository.NaverRepository

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
    private var naverRepository: NaverRepository = NaverRepository()

    override fun getMovieData(keyword: String) {
        if (keyword.contains("@")) {
            view.notAvailableKeyword()
            return
        }
        if (keyword.equals("")) {
            view.noKeyword()
            return
        }
        naverRepository.getMovieData(keyword, success = {
            if (it.item.isEmpty()) {
                view.noShowResult()
            } else {
                view.showResult(it)
            }
        },
            fail = {
                Log.e("error is :", it.toString())
            })
    }
}