package com.example.kotlinapplication.ui.view.home.presenter

import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.data.repository.DataRepositoryImpl
import com.example.kotlinapplication.data.source.local.LocalDataSourceImpl
import com.example.kotlinapplication.ui.view.page.PageContract

class BlogPresenter(listener: PageContract.View<BlogItem>) :
    PageContract.Presenter<BlogItem> {

    private val view: PageContract.View<BlogItem> = listener
    private val dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()
    private val localDataRepositoryImpl:LocalDataSourceImpl = LocalDataSourceImpl()

    override fun loadData(query: String) {
        dataRepositoryImpl.callBlogResources(query).subscribe(
            { datas -> view.getItems(datas.items) },
            { errorMessage -> view.getError("error 에러" + errorMessage) })
    }
    override fun getLocalItems():List<BlogItem> = localDataRepositoryImpl.getBlogCall()
}
