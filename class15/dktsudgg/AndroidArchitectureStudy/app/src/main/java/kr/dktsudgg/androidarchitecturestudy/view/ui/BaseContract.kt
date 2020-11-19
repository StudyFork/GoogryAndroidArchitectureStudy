package kr.dktsudgg.androidarchitecturestudy.view.ui

interface BaseContract {
    interface View {
        /**
         * 무언가 성공 시, 뷰에서 보여줄 처리 메소드
         */
        fun doSuccessAction(message: String)

        /**
         * 무언가 실패 시, 뷰에서 보여줄 처리 메소드
         */
        fun doFailureAction(message: String)

        /**
         * 빈 데이터를 다루게 될 시, 뷰에서 보여줄 처리 메소드
         */
        fun doWhenUseEmptyData(message: String)
    }

    interface Presenter {
        /**
         * 뷰로부터 가져온 데이터가 비어있는지 여부를 체크하는 메소드
         */
        fun isEmptyData(data: String): Boolean
    }
}