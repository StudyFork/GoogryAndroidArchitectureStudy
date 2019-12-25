package com.jay.architecturestudy.ui.image

import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.ui.BaseSearchContract

interface ImageContract {
    interface View : BaseSearchContract.View<Presenter, Image>

    interface Presenter : BaseSearchContract.Presenter
}