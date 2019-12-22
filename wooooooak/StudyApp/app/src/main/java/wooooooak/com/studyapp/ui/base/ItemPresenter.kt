package wooooooak.com.studyapp.ui.base

interface ItemPresenter<T> {
    suspend fun fetchItemsWithNewTitle(title: String)

    suspend fun fetchMoreItems(originList: List<T>, index: Int = 0)
}