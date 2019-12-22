package wooooooak.com.studyapp.ui.blog

import wooooooak.com.studyapp.data.model.repository.NaverApiRepository
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.ui.base.ItemContract

class BlogPresenter(
    private val view: ItemContract.View<Blog>,
    private val naverApiRepository: NaverApiRepository
) : ItemContract.Presenter<Blog> {

    private var userInputTitle = ""

    override suspend fun fetchItemsWithNewTitle(title: String) {
        try {
            if (title.isNotBlank()) {
                view.renderItems(naverApiRepository.getBlogList(title))
                userInputTitle = title
            }
        } catch (e: Exception) {
            view.renderErrorToast(e.localizedMessage)
        }
    }

    override suspend fun fetchMoreItems(originList: List<Blog>, index: Int) {
        try {
            if (userInputTitle.isNotBlank()) {
                val addedList = naverApiRepository.getBlogList(userInputTitle, index)
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