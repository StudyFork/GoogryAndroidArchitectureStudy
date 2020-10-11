package com.hong.architecturestudy.ui.moviedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.databinding.DialogFragmentMovieListBinding
import com.hong.architecturestudy.ui.main.MainViewModel
import com.hong.architecturestudy.ui.moviedialog.adapter.MovieSearchListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListDialogFragment : DialogFragment() {

    private val vm: MovieListDialogViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieListDialogViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())) as T
            }
        }
    }

    private val mainViewModel: MainViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())) as T
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
        movieAdapter.vm = mainViewModel

        with(binding) {
            rvSearchList.adapter = movieAdapter
            rvSearchList.setHasFixedSize(true)
            lifecycleOwner = viewLifecycleOwner
        }

        vm.movieInfo.observe {
            vm.movieInfo.value?.let {
                movieAdapter.setList(it)
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            vm.loadRecentSearchMovieList()
        }
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