package com.example.androidstudy.ui.base

import com.example.androidstudy.model.repository.NaverDataRepositoryImpl

class BasePresenter(val view : BaseContract.View) : BaseContract.Presenter {

    override fun search(query: String?, type: String) {
            NaverDataRepositoryImpl.getNaverSearchData(
                type,
                query!!,
                { result ->
                    view.onDataChanged(result.items)
                },
                { errMsg ->
                    view.showErrorMessage(errMsg)
                })
        }
}