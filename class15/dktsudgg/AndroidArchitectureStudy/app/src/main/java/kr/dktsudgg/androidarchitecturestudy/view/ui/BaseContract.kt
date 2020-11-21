package kr.dktsudgg.androidarchitecturestudy.view.ui

interface BaseContract {
    interface View {
        /**
         * 뷰에서 메세지를 보여주는 메소드
         */
        fun showToast(message: String)
    }

    interface Presenter {
        /**
         * 뷰로부터 가져온 데이터가 비어있는지 여부를 체크하는 메소드
         */
        fun isEmptyData(data: String): Boolean
    }
}