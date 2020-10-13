package com.hong.architecturestudy.ui.moviedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.databinding.DialogFragmentMovieListBinding
import com.hong.architecturestudy.ui.main.MainActivity
import com.hong.architecturestudy.ui.moviedialog.adapter.MovieSearchListAdapter

class MovieListDialogFragment : DialogFragment() {

    private val vm: MovieListDialogViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieListDialogViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())) as T
            }
        }
    }

    private lateinit var binding: DialogFragmentMovieListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_fragment_movie_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieSearchListAdapter()

        with(binding) {
            rvSearchList.adapter = movieAdapter
            rvSearchList.setHasFixedSize(true)
            lifecycleOwner = viewLifecycleOwner
        }

        vm.movieInfo observe {
            movieAdapter.setList(it)
        }

        movieAdapter.onClick = { query ->
            (requireActivity() as MainActivity).onQueryCallBack(query)
            dismiss()
        }

        vm.showRecentSearchMovieList()
    }

    private infix fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { observer(it) })
    }

    companion object {
        @Volatile
        private var INSTANCE: MovieListDialogFragment? = null

        fun getInstance(): MovieListDialogFragment =
            INSTANCE ?: synchronized(this) {
                MovieListDialogFragment().also { INSTANCE = it }
            }
    }

}