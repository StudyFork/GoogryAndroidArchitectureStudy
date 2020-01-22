package com.example.androidarchitecture.ui.movie


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.databinding.FragmentMovieBinding
import com.example.androidarchitecture.ui.base.BaseFragment
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    private lateinit var movieAdapter: MovieAdapter
    private val vm by lazy { MovieViewModel(naverSearchRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = vm
        binding.lifecycleOwner = this

        binding.recycle.run {
            movieAdapter = MovieAdapter()
            adapter = movieAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            vm.requestSearchHist()
        }

        observeListener()


    }

    private fun observeListener() {
        vm.blankInputText.observe(this, Observer {
            requireContext().toast(getString(R.string.black_input_text))
        })


        vm.errorToast.observe(this, Observer {
            requireContext().toast(it.toString())
        })

    }


}
