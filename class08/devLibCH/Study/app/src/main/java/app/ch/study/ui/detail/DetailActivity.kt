package app.ch.study.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.ch.study.BR
import app.ch.study.R
import app.ch.study.data.common.EXTRA_URL
import app.ch.study.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.setVariable(BR.itemUrl, intent.getStringExtra(EXTRA_URL))
    }

}
