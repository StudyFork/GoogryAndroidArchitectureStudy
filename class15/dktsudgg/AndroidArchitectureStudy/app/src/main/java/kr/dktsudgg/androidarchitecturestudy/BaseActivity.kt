package kr.dktsudgg.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutResId: Int) :
    AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 액티비티 종료 시, 이전 액티비티에 데이터 전달을 하기 위한 메소드
     */
    fun doActivityResult(returnKey: String?, returnData: String?) {
        if (returnKey != null && returnData != null) {
            setResult(RESULT_OK, Intent().putExtra(returnKey, returnData))
        } else {
            setResult(RESULT_OK)
        }

        finish()
    }

}