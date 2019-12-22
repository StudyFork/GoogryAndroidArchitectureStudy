package com.jay.architecturestudy.ui.kin

import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.ui.BaseContract

interface KinContract {
    interface View : BaseContract.View<Presenter, Kin>

    interface Presenter : BaseContract.Presenter
}