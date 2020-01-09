package com.example.architecture_project.ui.main

import android.util.Log
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.data.repository.NaverRepository

class MainPresenter(val view: MainContract.View) :
    MainContract.Presenter {
    private var naverRepository: NaverRepository = NaverRepository()

    override fun getMovieData(keyword: String) {
        if (keyword.contains("@")) {
            view.notAvailableKeyword()
            return
        }
        if (keyword.equals("")) {
            view.showNotExistKeyword()
            return
        }
        naverRepository.getMovieData(keyword, success = {
            if (it.item.isEmpty()) {
                view.showNoResult()
            } else {
                getMovieDataNum(it)
                view.showResult(it)

            }
        },
            fail = {
                Log.e("error is :", it.toString())
            })
    }

    override fun getMovieDataNum(data: NaverApi) {
        view.showDataNum(data.total)
    }
}