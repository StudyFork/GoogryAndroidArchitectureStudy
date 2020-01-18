package com.cnm.homework.ui

interface MainContract {

    interface View{
        fun showLoading()
//
        fun hideLoading()
//        fun showMovieList(items : List<>) 리스트: 아이템
//        adpater.resetAll(items) <- mainActivity에 넣어야됨
//          fl.empty.isvisible = false
//          rv_content.isVisible = true
        fun showErrorEmptyQuery()
//
        fun showErrorEmtpyResult()
//        fl.empty를 보여주고
//        rv_content를 false

    }

    interface Presenter {
        fun query(query : String)
    }

}