package kr.dktsudgg.androidarchitecturestudy

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.dktsudgg.androidarchitecturestudy.view.ui.BaseContract
import kr.dktsudgg.androidarchitecturestudy.view.ui.BasePresenter

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseContract.View {

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}