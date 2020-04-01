package com.byiryu.study.ui.mvp.login

import android.os.Bundle
import android.view.View
import com.byiryu.study.databinding.ActivityLoginBinding
import com.byiryu.study.ui.mvp.base.BaseActivity
import com.byiryu.study.ui.mvp.base.BaseContract
import com.byiryu.study.ui.mvp.base.BasePresenter
import com.byiryu.study.ui.mvp.main.MainActivity

class LoginActivity : BaseActivity(), LoginContract.View {

    @Suppress("UNCHECKED_CAST")
    override val presenter: BaseContract.Presenter<BaseContract.View>
        get() = loginPresenter as BasePresenter<BaseContract.View>

    private val loginPresenter by lazy {
        LoginPresenter<LoginContract.View>(getBRApplication().repository)
    }
    override var progressBar: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewDataBinding = ActivityLoginBinding.inflate(layoutInflater).apply {
//            progressBar = loading

            btnLogin.setOnClickListener {
                loginPresenter.login(
                    editTextId.text.toString(),
                    editTextPw.text.toString(),
                    btnAutoLogin.isChecked
                )
            }
        }
        setContentView(viewDataBinding.root)
    }

    override fun goActivityMain() {
        goActivity(MainActivity::class.java)
        finish()
    }

}