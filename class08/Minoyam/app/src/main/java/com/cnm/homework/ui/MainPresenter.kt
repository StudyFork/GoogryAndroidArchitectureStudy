package com.cnm.homework.ui

import com.cnm.homework.data.repository.NaverQueryRepository

class MainPresenter(private val view : MainContract.View) : MainContract.Presenter {

    private lateinit var naverSearchRepository: NaverQueryRepository

    override fun query(query: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        /*
        if(query.isEmpty()){
        view.showErrorEmptyQuery()
        return
         */

        /*
            naverSearchRetory.add()
            .doOnsucribe {view.showLoading()}
            .doAfterTerminate { view.hideLoading()}
            .subscribe({
            if(it.isempty)
            {
            view.showErrorEmptyResult
            }
            else
            {
            view.showMoiveList(it)
            }
         */
    }

}