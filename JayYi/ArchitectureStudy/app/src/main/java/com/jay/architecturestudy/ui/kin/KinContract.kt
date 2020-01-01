package com.jay.architecturestudy.ui.kin

import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.ui.BaseSearchContract

interface KinContract {
    interface View : BaseSearchContract.View<Presenter, Kin> {
        fun updateUi(keyword: String, kins: List<Kin>)
    }

    interface Presenter : BaseSearchContract.Presenter
}