package com.jay.architecturestudy.ui.blog

import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.ui.BaseContract

interface BlogContract {

    interface View : BaseContract.View<Presenter, Blog>

    interface Presenter : BaseContract.Presenter

}