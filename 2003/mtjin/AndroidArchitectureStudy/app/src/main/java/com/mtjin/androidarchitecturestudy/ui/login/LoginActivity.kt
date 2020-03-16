package com.mtjin.androidarchitecturestudy.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mtjin.androidarchitecturestudy.R

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var etId: EditText
    private lateinit var etPw: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initListener()
        presenter = LoginPresenter(this)
    }

    private fun initView() {
        etId = findViewById(R.id.et_id)
        etPw = findViewById(R.id.et_pw)
        btnLogin = findViewById(R.id.btn_login)
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            val id = etId.text.toString().trim()
            val pw = etPw.text.toString().trim()
            presenter.login(this, id, pw)
        }
    }


    override fun showLoginErrorToast() {
        Toast.makeText(this, "아이디 또는 패스워드가 틀렸습니다", Toast.LENGTH_SHORT).show()
    }

    override fun showIdEmptyError() {
        etId.error = "아이디를 입력해주세요"
    }

    override fun showPwEmptyError() {
        etPw.error = "비밀번호를 입력해주세요"
    }

    override fun finishActivity() {
        finish()
    }


}
