package com.mtjin.androidarchitecturestudy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.mtjin.androidarchitecturestudy.R

class LoginActivity : AppCompatActivity() {
    private lateinit var tvId : TextView
    private lateinit var tvPw : TextView
    private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initListener()
    }

    private fun initView() {
        tvId = findViewById(R.id.tv_id)
        tvPw = findViewById(R.id.tv_pw)
        btnLogin = findViewById(R.id.btn_login)
    }

    private fun initListener() {
        btnLogin.setOnClickListener{
            val id = tvId.text.toString().trim()
            val pw = tvPw.text.toString().trim()
            if(id == USER_ID && pw == USER_PW){
                startActivity(Intent(this, MovieSearchActivity::class.java))
            }else if(id.isEmpty()){
                tvId.error = "아이디를 입력해주세요"
            }else if(pw.isEmpty()){
                tvPw.error = "비밀번호를 입력해주세요"
            }else{
                tvPw.error ="아이디와 또는 패스워드가 틀렸습니다"
            }
        }
    }

    companion object{
        const val USER_ID = "id"
        const val USER_PW = "P@sswOrd"
    }
}
