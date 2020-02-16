package com.example.architecturestudy.ui.blog

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.Injection
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.databinding.FragmentBlogBinding
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : Fragment(), BlogContract.View {

    private lateinit var binding: FragmentBlogBinding

    private lateinit var blogAdapter: BlogAdapter

    private val presenter : BlogContract.Presenter by lazy {
        BlogPresenter(
            this,
            context?.let { Injection.provideNaverSearchRepository(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_blog,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         blogAdapter = BlogAdapter()

        presenter.getLastData()

        binding.recycleview.apply {
        // 화면 켯을 때 이전 데이터 가져옴
            adapter = blogAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            )
        }

        binding.btnSearch.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                presenter.taskSearch(
                        isNetWork = isOnline(),
                        keyword = edit
                    )
            }
        }
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showEmpty(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showResult(item: List<BlogItem>) {
        blogAdapter.update(item)
    }

    private fun isOnline(): Boolean {
        val connMgr = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}