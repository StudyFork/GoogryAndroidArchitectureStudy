package com.hong.architecturestudy.ui.moviedialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.ui.main.GetMovieTitle
import com.hong.architecturestudy.ui.moviedialog.adapter.MovieSearchListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListDialogFragment(private val getMovieTitle: GetMovieTitle) : DialogFragment(),
    MovieListDialogContract.View {

    private val movieListDialogPresenter: MovieListDialogContract.Presenter by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        MovieListDialogPresenter(
            this, RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl)
        )
    }

    private val movieSearchListAdapter: MovieSearchListAdapter by lazy {
        MovieSearchListAdapter {
            getMovieTitle(it)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment_movie_list, null)
        val rvSearchItem = view?.findViewById<RecyclerView>(R.id.rv_search_list)

        rvSearchItem?.adapter = movieSearchListAdapter
        rvSearchItem?.setHasFixedSize(true)

        lifecycleScope.launch(Dispatchers.Default) {
            movieListDialogPresenter.loadRecentSearchMovieList()
        }

        return AlertDialog.Builder(requireActivity())
            .setView(view)
            .setTitle("최근 검색")
            .create()
    }

    companion object {
        @Volatile
        private var INSTANCE: MovieListDialogFragment? = null

        fun getInstance(param: (String) -> Unit): MovieListDialogFragment =
            INSTANCE ?: synchronized(this) {
                MovieListDialogFragment(param).also { INSTANCE = it }
            }
    }

    override fun loadRecentQuery(movieInfo: List<MovieInfo>) {
        movieSearchListAdapter.setList(movieInfo)
    }
}