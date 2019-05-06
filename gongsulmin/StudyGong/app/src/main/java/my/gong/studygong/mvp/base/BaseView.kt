package my.gong.studygong.mvp.base

interface BaseView<T> {
    var presenter: T
    fun showToast(msg: String)
}