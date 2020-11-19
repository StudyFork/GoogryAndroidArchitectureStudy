package kr.dktsudgg.androidarchitecturestudy

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.dktsudgg.androidarchitecturestudy.view.ui.BaseContract
import kr.dktsudgg.androidarchitecturestudy.view.ui.BasePresenter

open class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseContract.View {
    override fun doSuccessAction(message: String) {
        // 성공 메세지 띄우기
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun doFailureAction(message: String) {
        // 실패 메세지 띄우기
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun doWhenUseEmptyData(message: String) {
        // 빈 데이터를 다루게 될 시 안내 메세지 띄우기
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}