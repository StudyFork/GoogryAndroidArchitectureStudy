package com.jay.architecturestudy.ui.blog

import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.ui.BaseSearchContract

interface BlogContract {

    interface View : BaseSearchContract.View<Presenter, Blog>

    interface Presenter : BaseSearchContract.Presenter

}