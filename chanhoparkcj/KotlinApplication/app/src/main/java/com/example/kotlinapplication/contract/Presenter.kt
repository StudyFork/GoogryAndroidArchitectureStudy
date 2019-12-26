package com.example.kotlinapplication.contract

import com.example.kotlinapplication.model.repository.DataRepositoryImpl

class Presenter(listener: Contract.View) : Contract.Presenter {
    private var view: Contract.View = listener
    private var dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()

    override fun loadData(type: String?, query: String) {
        when (type) {
            "영화" -> dataRepositoryImpl.callMovieResources(query).subscribe(
                { datas -> view.resultMovie(datas.items) },
                { errorMessage -> view.resultError("error 에러" + errorMessage) })
            "이미지" -> dataRepositoryImpl.callImageResources(query).subscribe(
                { datas -> view.resultImage(datas.items) },
                { errorMessage -> view.resultError("error 에러" + errorMessage) })
            "블로그" -> dataRepositoryImpl.callBlogResources(query).subscribe(
                { datas -> view.resultBlog(datas.items) },
                { errorMessage -> view.resultError("error 에러" + errorMessage) })
            "지식인" -> dataRepositoryImpl.callKinResources(query).subscribe(
                { datas -> view.resultKin(datas.items) },
                { errorMessage -> view.resultError("error 에러" + errorMessage) })
            else -> {
                view.resultError("에러")
            }
        }
    }

}