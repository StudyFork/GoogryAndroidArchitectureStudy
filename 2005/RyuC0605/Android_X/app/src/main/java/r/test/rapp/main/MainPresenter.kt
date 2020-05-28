package r.test.rapp.main

import android.text.TextUtils
import r.test.rapp.R
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Present {

    private val repository: MovieRepository = MovieRepositoryImpl()

    override fun searchData(keyword: String) {

        if (TextUtils.isEmpty(keyword)) {
            view.showToast(R.string.enter_keyword)
            return
        }
        view.showProgress()

        repository.getMovieList(
            keyword,
            onSuccess = { vo ->
                view.refreshListView(vo.items)
                view.hideProgress()
            },
            onFail = { f ->
                view.showToast(f.toString())
                view.hideProgress()
            });
    }
}