package com.byiryu.study.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.byiryu.study.ui.BRApplication

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    protected abstract var progressBar: View?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)

    }
    override fun showMsg(res: Int) {
        showMsg(getString(res))
    }

    override fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar?.visibility = View.INVISIBLE
    }

    override fun goActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    override fun goActivity(intent: Intent) {
        startActivity(intent)
    }

    fun getBRApplication() : BRApplication{
        return applicationContext as BRApplication
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}