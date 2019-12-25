package wooooooak.com.studyapp.ui.movie

import wooooooak.com.studyapp.data.model.repository.NaverApiRepository
import wooooooak.com.studyapp.data.model.response.movie.Movie
import wooooooak.com.studyapp.ui.base.ItemContract

class MoviePresenter(
    private val view: ItemContract.View<Movie>,
    private val naverApiRepository: NaverApiRepository
) : ItemContract.Presenter<Movie> {

    private var userInputTitle = ""

    override suspend fun fetchItemsWithNewTitle(title: String) {
        try {
            if (title.isNotBlank()) {
                view.renderItems(naverApiRepository.getMovieList(title))
                userInputTitle = title
            }
        } catch (e: Exception) {
            view.renderErrorToast(e.localizedMessage)
        }
    }

    override suspend fun fetchMoreItems(originList: List<Movie>, index: Int) {
        try {
            if (userInputTitle.isNotBlank()) {
                val addedList = naverApiRepository.getMovieList(userInputTitle, index)
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