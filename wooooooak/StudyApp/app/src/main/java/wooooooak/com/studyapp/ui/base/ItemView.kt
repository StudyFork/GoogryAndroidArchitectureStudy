package wooooooak.com.studyapp.ui.base

interface ItemView<T> {
    fun renderItems(items: List<T>)

    fun renderWebView(url: String)

    fun renderErrorToast(message: String?)
}