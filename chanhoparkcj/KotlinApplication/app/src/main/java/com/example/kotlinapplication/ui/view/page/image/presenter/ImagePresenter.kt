package com.example.kotlinapplication.ui.view.home.presenter

import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.data.repository.DataRepositoryImpl
import com.example.kotlinapplication.ui.view.page.PageContract

class ImagePresenter(listener: PageContract.View<ImageItem>) :
    PageContract.Presenter {
    private val view: PageContract.View<ImageItem> = listener
    private val dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()

    override fun loadData(query: String) {
        dataRepositoryImpl.callImageResources(query).subscribe(
            { datas -> view.getItems(datas.items) },
            { errorMessage -> view.getError("error 에러" + errorMessage) })
    }
}
