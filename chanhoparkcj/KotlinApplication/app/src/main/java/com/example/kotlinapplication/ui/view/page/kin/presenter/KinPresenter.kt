package com.example.kotlinapplication.ui.view.home.presenter

import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.data.repository.DataRepositoryImpl
import com.example.kotlinapplication.data.source.local.LocalDataSourceImpl
import com.example.kotlinapplication.ui.view.page.PageContract

class KinPresenter(listener: PageContract.View<KinItem>) :
    PageContract.Presenter<KinItem> {
    private val view: PageContract.View<KinItem> = listener
    private val dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()
    private val localDataRepositoryImpl: LocalDataSourceImpl = LocalDataSourceImpl()

    override fun loadData(query: String) {
        dataRepositoryImpl.callKinResources(query).subscribe(
            { datas -> view.getItems(datas.items) },
            { errorMessage -> view.getError("error 에러" + errorMessage) })
    }
    override fun getLocalItems():List<KinItem>? = localDataRepositoryImpl.getKinCall()
}
