package wooooooak.com.studyapp.ui.base

interface ItemContract {
    interface View<T> {
        fun renderItems(items: List<T>)

        fun renderWebView(url: String)

        fun renderErrorToast(message: String?)
    }

    interface Presenter<T> {
        suspend fun fetchItemsWithNewTitle(title: String)

        suspend fun fetchMoreItems(originList: List<T>, index: Int = 0)
    }
}