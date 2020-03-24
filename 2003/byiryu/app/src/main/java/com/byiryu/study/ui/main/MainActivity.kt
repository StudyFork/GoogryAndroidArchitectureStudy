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

        val viewDataBinding = ActivityMainBinding.inflate(layoutInflater).apply {
            progressBar = loading
            adapter = this@MainActivity.adapter
            editText.hint = mainPresenter.getPrevQuery()

            btnSearch.setOnClickListener {
                mainPresenter.search(editText.text.toString())
            }
        }

        setContentView(viewDataBinding.root)

    }

    override fun setResult(items: List<MovieItem>) {
        adapter.submitList(items)
    }
}
