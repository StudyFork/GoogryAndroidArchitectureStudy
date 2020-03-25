package com.byiryu.study.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.byiryu.study.databinding.ActivityMainBinding
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.base.BaseContract
import com.byiryu.study.ui.base.BasePresenter
import com.byiryu.study.wigets.OnViewClickListener

class MainActivity : BaseActivity(), MainConract.View{

    @Suppress("UNCHECKED_CAST")
    override val presenter: BaseContract.Presenter<BaseContract.View>
        get() = mainPresenter as BasePresenter<BaseContract.View>

    private val mainPresenter: MainConract.Presenter<MainConract.View> by lazy {
        MainPresenter<MainConract.View>(getBRApplication().repository)
    }

    override var progressBar: View? = null

    private lateinit var viewDataBinding : ActivityMainBinding

    private val adapter: MainRecyclerAdapter = MainRecyclerAdapter(onViewClickListener = object : OnViewClickListener{
        override fun onclick(data: Any) {
            if(data !is MovieItem){
                return
            }

            goActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.link)))
        }

    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         viewDataBinding = ActivityMainBinding.inflate(layoutInflater).apply {
            progressBar = loading
            adapter = this@MainActivity.adapter

            btnSearch.setOnClickListener {
                mainPresenter.search(editText.text.toString())
            }
        }

        mainPresenter.getPrevQuery()

        setContentView(viewDataBinding.root)

    }

    override fun setResult(items: List<MovieItem>) {
        adapter.submitList(items)
    }

    override fun setPrevQuery(query: String) {
        viewDataBinding.editText.hint = query
    }
}
