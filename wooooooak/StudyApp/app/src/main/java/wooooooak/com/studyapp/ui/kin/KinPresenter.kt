package wooooooak.com.studyapp.ui.kin

import wooooooak.com.studyapp.data.model.repository.NaverApiRepository
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.ui.base.ItemContract

class KinPresenter(
    private val view: ItemContract.View<Kin>,
    private val naverApiRepository: NaverApiRepository
) : ItemContract.Presenter<Kin> {

    private val userInputTitle
        get() = naverApiRepository.lastKinTitle

    override suspend fun fetchItemsWithNewTitle(title: String, cached: Boolean) {
        try {
            when {
                cached || title.isNotBlank() -> {
                    val items = naverApiRepository.getKinList(title, cached = cached)
                    view.renderListEmptyView(items.isEmpty())
                    view.renderItems(items)
                    view.setTitle(userInputTitle)
                }
                else -> view.renderEmptyTitleErrorToast()
            }
        } catch (e: Exception) {
            view.renderErrorToast(e.localizedMessage)
        }
    }

    override suspend fun fetchMoreItems(originList: List<Kin>, index: Int) {
        try {
            if (userInputTitle.isNotBlank()) {
                val addedList = naverApiRepository.getKinList(userInputTitle, index)
                val newList = originList.toMutableList().apply {
                    addAll(addedList)
                }
                view.renderItems(newList)
            }
        } catch (e: Exception) {
            view.renderErrorToast(e.localizedMessage)
        }
    }
}