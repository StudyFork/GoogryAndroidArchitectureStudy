package com.jay.architecturestudy.ui.blog

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.databinding.FragmentBlogBinding
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.then

class BlogFragment : BaseFragment<FragmentBlogBinding, BlogViewModel>(R.layout.fragment_blog) {
    override val viewModel: BlogViewModel by lazy {
        BlogViewModel(naverSearchRepository)
    }

    private lateinit var blogAdapter: BlogAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onCleared()
    }
}