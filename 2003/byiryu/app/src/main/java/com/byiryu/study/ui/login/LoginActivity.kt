package com.byiryu.study.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.byiryu.study.R
import com.byiryu.study.ui.base.BaseActivity
import com.byiryu.study.ui.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private var id: String? = null
    private var pw: String? = null

    override val progressBar: View?
        get() = loading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        bind()
    }

    private fun init() {
    }

    @SuppressLint("CheckResult")
    private fun bind() {

        btn_login.setOnClickListener {
            id = editText_id.text.toString()
            pw = editText_pw.text.toString()
            if (invalid()) {
                getBRApplication().repository.loginCheck(id!!, pw!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showLoading()
                    }.doOnError {
                        showMsg(R.string.msg_error_loading)
                    }
                    .subscribe({
                        Log.e("TAG", it.toString())
                        if (it) {
                            if (btn_auto_login.isChecked)
                                getBRApplication().repository.setAutoLogin()
                            goActivity(MainActivity::class.java)
                            hideLoading()
                            finish()
                        } else {
                            hideLoading()
                            showMsg(R.string.msg_incorrect_login)
                        }
                    }, {
                        showMsg("오류 발생 : $it")
                    })
            }
        }

    }

    private fun invalid(): Boolean {
        if (id!!.isEmpty()) {
            showMsg(R.string.msg_invalid_id)
            return false
        }

        if (pw!!.isEmpty()) {
            showMsg(R.string.msg_invalid_pw)
            return false
        }

        return true
    }


}