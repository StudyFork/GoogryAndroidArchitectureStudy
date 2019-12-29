package wooooooak.com.studyapp.ui.image

import wooooooak.com.studyapp.data.model.repository.NaverApiRepository
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.ui.base.ItemContract

class ImagePresenter(
    private val view: ItemContract.View<Image>,
    private val naverApiRepository: NaverApiRepository
) : ItemContract.Presenter<Image> {

    private val userInputTitle
        get() = naverApiRepository.lastImageTitle

    override suspend fun fetchItemsWithNewTitle(title: String, cached: Boolean) {
        try {
            when {
                cached || title.isNotBlank() -> {
                    val items = naverApiRepository.getImageList(title, cached = cached)
                    if (items.isEmpty()) {
                        view.renderListEmptyView(true)
                    } else {
                        view.renderListEmptyView(false)
                        view.renderItems(items)
                    }
                }
                else -> view.renderEmptyTitleErrorToast()
            }
            view.setTitle(userInputTitle)
        } catch (e: Exception) {
            view.renderErrorToast(e.localizedMessage)
        }
    }

    override suspend fun fetchMoreItems(originList: List<Image>, index: Int) {
        try {
            if (userInputTitle.isNotBlank()) {
                val addedList = naverApiRepository.getImageList(userInputTitle, index)
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