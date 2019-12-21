package com.buddman1208.architecturestudy.ui

import com.buddman1208.architecturestudy.repo.NaverDataRepositoryImpl
import com.buddman1208.architecturestudy.utils.subscribeOnIO
import io.reactivex.android.schedulers.AndroidSchedulers

class BasePresenter(val view : BaseContract.View) : BaseContract.Presenter {

    override fun searchByQuery(query: String, type: String) {
        val query = query.trim()
        if (query.isNotBlank()) {
            NaverDataRepositoryImpl
                .searchByTypeFromNaver(
                    searchType = type,
                    query = query
                )
                .subscribeOnIO()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        // TODO : onSuccess
                    },
                    {
                        // TODO : onFailure
                    }
                )


        } else {
            // TODO : Must show blank query error
        }
    }

}