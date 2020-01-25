package com.example.architecturestudy.ui.blog

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.Injection
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.BlogItem
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : Fragment(), BlogContract.View {
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
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         blogAdapter = BlogAdapter()

        // 화면 켯을 때 이전 데이터 가져옴
        presenter.getLastData()

        recycleview.apply {
            adapter = blogAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            )
        }

        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                presenter.apply {
                    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                    val activeNetwork: NetworkInfo? = cm?.activeNetworkInfo
                    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

                    Log.e("isConnected", "$isConnected")

                    taskSearch(
                        isNetWork = isConnected,
                        keyword = edit
                    )
                }
            }
        }
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT)
    }

    override fun showEmpty(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT)
    }

    override fun showResult(item: List<BlogItem>) {
        blogAdapter.update(item)
    }
}