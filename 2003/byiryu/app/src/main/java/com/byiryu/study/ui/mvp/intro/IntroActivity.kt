package com.byiryu.study.ui.mvp.intro

import android.os.Bundle
import android.view.View
import com.byiryu.study.databinding.ActivityIntroBinding
import com.byiryu.study.ui.mvp.base.BaseActivity
import com.byiryu.study.ui.mvp.base.BaseContract
import com.byiryu.study.ui.mvp.base.BasePresenter
import com.byiryu.study.ui.mvp.login.LoginActivity
import com.byiryu.study.ui.mvp.main.MainActivity


class IntroActivity : BaseActivity(), IntroContract.View{

    private lateinit var binding : ActivityIntroBinding
    @Suppress("UNCHECKED_CAST")
    override val presenter: BaseContract.Presenter<BaseContract.View>
        get() = introPresenter as BasePresenter<BaseContract.View>

    private val introPresenter by lazy {
        IntroPresenter<IntroContract.View>(getBRApplication().repository)
    }
    override var progressBar: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun goActivityMain() {
        goActivity(MainActivity::class.java)
        finish()
    }

    override fun goActivityLogin() {
        goActivity(LoginActivity::class.java)
        finish()
    }


}