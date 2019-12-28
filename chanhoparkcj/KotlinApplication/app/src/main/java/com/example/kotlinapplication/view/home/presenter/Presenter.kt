package com.example.kotlinapplication.view.home.presenter

import com.example.kotlinapplication.data.repository.DataRepositoryImpl
import com.example.kotlinapplication.view.home.PageFragment

class Presenter(listener: Contract.View) :
    Contract.Presenter {
    private val view: Contract.View = listener
    private val dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()

    override fun loadData(type: Int?, query: String) {
        when (type) {
            PageFragment.MOVIE_VIEW -> dataRepositoryImpl.callMovieResources(query).subscribe(
                { datas -> view.getMovie(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            PageFragment.IMAGE_VIEW -> dataRepositoryImpl.callImageResources(query).subscribe(
                { datas -> view.getImage(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            PageFragment.BLOG_VIEW -> dataRepositoryImpl.callBlogResources(query).subscribe(
                { datas -> view.getBlog(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            PageFragment.KIN_VIEW -> dataRepositoryImpl.callKinResources(query).subscribe(
                { datas -> view.getKin(datas.items) },
                { errorMessage -> view.getError("error 에러" + errorMessage) })
            else -> {
                view.getError("에러")
            }
        }
    }

}