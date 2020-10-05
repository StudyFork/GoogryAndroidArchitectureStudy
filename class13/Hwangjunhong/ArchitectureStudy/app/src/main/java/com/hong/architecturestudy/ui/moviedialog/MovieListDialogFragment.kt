package com.hong.architecturestudy.ui.moviedialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.Observable
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
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

    private val vm by lazy {
        MovieListDialogViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl()))
    }

    var mainViewModel: MainViewModel? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = DialogFragmentMovieListBinding.bind(
            requireActivity().layoutInflater.inflate(
                R.layout.dialog_fragment_movie_list,
                null
            )
        )

        val movieAdapter = MovieSearchListAdapter()
        movieAdapter.vm = mainViewModel
        binding.rvSearchList.adapter = movieAdapter
        binding.rvSearchList.setHasFixedSize(true)

        vm.movieInfo.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.movieInfo.get()?.let {
                    movieAdapter.setList(it)
                }
            }

        })

        lifecycleScope.launch(Dispatchers.IO) {
            vm.loadRecentSearchMovieList()
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setTitle("최근 검색")
            .create()
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