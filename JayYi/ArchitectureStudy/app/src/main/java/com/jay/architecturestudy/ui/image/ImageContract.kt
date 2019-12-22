package com.jay.architecturestudy.ui.image

import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.ui.BaseContract

interface ImageContract {
    interface View : BaseContract.View<Presenter, Image>

    interface Presenter : BaseContract.Presenter
}