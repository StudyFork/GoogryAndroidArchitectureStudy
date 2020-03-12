package com.byiryu.study.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.network.NetworkService.retrofit
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var repository : Repository

    override val progressBar: View
        get() = loading

    private var disposable: Disposable? = null

    private val adapter = MainRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()

    }

    private fun bind() {

        repository = Repository(this@MainActivity)

        recyclerView.adapter = adapter

        adapter.setOnclickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))
        }

        btn_search.setOnClickListener {
            val text = editText.text.toString()


            if (text.isEmpty()) {
                showMsg(R.string.msg_search_value)
            } else {
                disposable = repository.getMovieList(text)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showLoading()
                    }.doOnSuccess{
                        hideLoading()
                    }.doOnError {
                        showMsg(R.string.msg_error_loading)
                    }
                    .subscribe({
                        adapter.submitList(it)
                    },{
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
