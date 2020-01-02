package wooooooak.com.studyapp.ui.base

interface ItemContract {
    interface View<T> {
        fun setTitle(title: String)

        fun renderItems(items: List<T>)

        fun renderWebView(url: String)

        fun renderErrorToast(message: String?)

        fun renderEmptyTitleErrorToast()

        fun renderListEmptyView(shouldRender: Boolean)
    }

    interface Presenter<T> {
        suspend fun fetchItemsWithNewTitle(title: String, cached: Boolean = false)

        suspend fun fetchMoreItems(originList: List<T>, index: Int = 0)

    }
}