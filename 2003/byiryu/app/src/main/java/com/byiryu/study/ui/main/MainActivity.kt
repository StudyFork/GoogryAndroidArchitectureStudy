package com.byiryu.study.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val progressBar: View
        get() = loading

    private var disposable: Disposable? = null

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

        editText.hint = getBRApplication().repository.getPrevSearchQuery()

    }

    private fun bind() {

        adapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }

        btn_search.setOnClickListener {
            val text = editText.text.toString()

            if (text.isEmpty()) {
                showMsg(R.string.msg_search_value)
            } else {
                disposable = getBRApplication().repository.getMovieList(text)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showLoading()
                    }.doOnSuccess {
                        hideLoading()
                    }.doOnError {
                        showMsg(R.string.msg_error_loading)
                    }
                    .subscribe({
                        adapter.submitList(it)
                    }, {
                        showMsg("오류 발생 : $it")
                    })
            }
        }
    }

    override fun onDestroy() {

        disposable?.dispose()
        super.onDestroy()

    }

}
