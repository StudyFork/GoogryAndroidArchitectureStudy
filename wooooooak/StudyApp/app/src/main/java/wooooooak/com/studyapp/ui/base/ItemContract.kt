package wooooooak.com.studyapp.ui.base

interface ItemContract {
    interface View<T> : ItemView<T>

    interface Presenter<T> : ItemPresenter<T>
}