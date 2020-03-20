package com.byiryu.study.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.base.BaseActivity
import io.reactivex.disposables.Disposable

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainConract.View {

    private val presenter: MainConract.Presenter<MainConract.View> by lazy {
        MainPresenter<MainConract.View>(getBRApplication().repository)
    }

    override val progressBar: View
        get() = loading

    private var disposable: Disposable? = null

    private lateinit var adapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onAttach(this)

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
            presenter.search(editText.text.toString())
        }


    }

    override fun setResult(items: List<MovieItem>) {
        adapter.submitList(items)
    }

    override fun setPrevQuery(query: String) {
        editText.hint = query
    }


    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()

    }

}
