package com.jay.architecturestudy.ui.blog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.databinding.FragmentBlogBinding
import com.jay.architecturestudy.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlogFragment : BaseFragment<FragmentBlogBinding, BlogViewModel>(R.layout.fragment_blog) {
    override val viewModel: BlogViewModel by viewModel()

    private lateinit var blogAdapter: BlogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { activity ->
            blogAdapter = BlogAdapter()
            binding.recyclerView.run {
                adapter = blogAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }
}