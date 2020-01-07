package com.song2.practice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.song2.practice.R

class MainActivity : AppCompatActivity(), MainContract.View {

    //private var presenter : MainContract.Presenter by lazy{}
    private lateinit var presenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)

        //버튼클릭되었을 경우,
        presenter.signin()

    }

    override fun showSignupSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorEmailEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorPasswordEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorNotExist() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
