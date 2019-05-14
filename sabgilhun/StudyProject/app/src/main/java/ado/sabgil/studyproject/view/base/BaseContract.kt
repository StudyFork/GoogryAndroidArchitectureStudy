package ado.sabgil.studyproject.view.base

interface BaseContract {

    interface Presenter {

        fun unSubscribe()

    }

    interface View {

        fun showToastMessage(msg: String)

    }

}