package kr.dktsudgg.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kr.dktsudgg.androidarchitecturestudy.view.ui.mvp.BaseContract
import kr.dktsudgg.androidarchitecturestudy.view.ui.mvp.BasePresenter

abstract class BaseActivity<P : BasePresenter, B : ViewDataBinding>(@LayoutRes val layoutResId: Int) :
    AppCompatActivity(), BaseContract.View {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    override fun showToast(message: String) {
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