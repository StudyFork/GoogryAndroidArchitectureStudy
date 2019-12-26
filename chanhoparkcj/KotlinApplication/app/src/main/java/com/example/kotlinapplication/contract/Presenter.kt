package com.example.kotlinapplication.contract

import com.example.kotlinapplication.model.repository.DataRepositoryImpl

class Presenter(listener: Contract.View) : Contract.Presenter {
    private val view: Contract.View = listener
    private val dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()

    override fun loadData(type: String?, query: String) {
        when (type) {
            "영화" -> dataRepositoryImpl.callMovieResources(query).subscribe(
                { datas -> view.getMovie(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            "이미지" -> dataRepositoryImpl.callImageResources(query).subscribe(
                { datas -> view.getImage(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            "블로그" -> dataRepositoryImpl.callBlogResources(query).subscribe(
                { datas -> view.getBlog(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            "지식인" -> dataRepositoryImpl.callKinResources(query).subscribe(
                { datas -> view.getKin(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            else -> {
                view.getError("에러")
            }
        }
    }

}