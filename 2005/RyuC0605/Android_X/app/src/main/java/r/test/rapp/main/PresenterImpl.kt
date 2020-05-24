package r.test.rapp.main

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import r.test.rapp.data.model.Item
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl

class PresenterImpl : Contractor.Presenter {

    private var repository: MovieRepository = MovieRepositoryImpl()


    private var view: Contractor.View? = null;

    override fun setView(v: Contractor.View) {
        this.view = v;
    }

    override fun searchData(keyword: String) {
        repository = repository ?: MovieRepositoryImpl()
        repository?.getMovieList(
            keyword,
            onSuccess = { vo ->
                val res = vo ?: return@getMovieList
                view?.refreshListView(res.items)
                view?.hideProgress()
            },
            onFail = { f ->
                view?.showToast(f.toString())
                view?.hideProgress()
            });
    }
}