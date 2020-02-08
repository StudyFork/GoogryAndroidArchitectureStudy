package com.example.androidarchitecture.ui.blog


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        binding.lifecycleOwner = this

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
        // Fragment 에서 observe 는 this 가 아니라 viewLifecycleOwner.
        // Fragment의 구성이 변경되는 동안 destroy 를 안타고 destroyview 까지만 탈 수 있다.
        // 고로 뷰는 파과되었지만, 해당 프래그먼트의 인스턴스는 남아있고, 다시 해당 프레그먼트를 불러올시, 동일한 observer 가 생길 수 있다.
        vm.blankInputText.observe(viewLifecycleOwner, Observer {
            requireContext().toast(getString(R.string.black_input_text))
        })


        vm.errorToast.observe(viewLifecycleOwner, Observer {
            requireContext().toast(it.toString())
        })

    }

}
