package com.byiryu.study.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.base.BaseContract
import com.byiryu.study.ui.base.BasePresenter

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainConract.View {

    @Suppress("UNCHECKED_CAST")
    override val presenter: BaseContract.Presenter<BaseContract.View>
        get() = mainPresenter as BasePresenter<BaseContract.View>

    private val mainPresenter: MainConract.Presenter<MainConract.View> by lazy {
        MainPresenter<MainConract.View>(getBRApplication().repository)
    }

    override val progressBar: View
        get() = loading

    private lateinit var adapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        bind()

    }

    private fun initView() {

        adapter = MainRecyclerAdapter()

        recyclerView.adapter = adapter


    }

    private fun bind() {

        adapter.setOnclickListener {
            goActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }

        btn_search.setOnClickListener {
            mainPresenter.search(editText.text.toString())
        }


    }

    override fun setResult(items: List<MovieItem>) {
        adapter.submitList(items)
    }

    override fun setPrevQuery(query: String) {
        editText.hint = query
    }

}
