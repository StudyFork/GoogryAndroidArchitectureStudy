package com.example.androidarchitecture.ui.blog


import android.os.Bundle
import android.view.View
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.databinding.FragmentBlogBinding
import com.example.androidarchitecture.ui.base.BaseFragment
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class BlogFragment : BaseFragment<FragmentBlogBinding>(R.layout.fragment_blog) {

    private lateinit var blogAdapter: BlogAdapter
    private val vm by lazy {
        BlogViewModel(
            naverSearchRepository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = vm

        binding.recycle.run {
            blogAdapter = BlogAdapter()
            adapter = blogAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            vm.requestSearchHist()
        }

        observeListener()


    }

    private fun observeListener() {
        vm.blankInputText.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                requireContext().toast(getString(R.string.black_input_text))
            }

        })

        vm.errorToast.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                requireContext().toast(vm.errorToast.get().toString())
            }

        })

    }


}
