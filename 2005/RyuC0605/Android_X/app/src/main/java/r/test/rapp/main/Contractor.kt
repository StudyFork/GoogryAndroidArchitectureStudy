package r.test.rapp.main

import r.test.rapp.data.model.Item

interface Contractor {

    interface View {

        /**
         * 토스트 메시지 출력
         */
        fun showToast(msg: String)

        /**
         * 프로그레스 보여주기.
         */
        fun showProgress()

        /**
         * 프로그레스 숨기기
         */
        fun hideProgress()

        /**
         * 키패드 숨기기
         */
        fun hideKeyPad()

        /**
         * 키패드 보여주기.
         */
        fun showKeyPad()

        /**
         * 리스트뷰 갱신
         */
        fun refreshListView(items: List<Item>)

    }

    interface Presenter {

        fun setView(v: View)
        fun searchData(keyword: String)

    }
}