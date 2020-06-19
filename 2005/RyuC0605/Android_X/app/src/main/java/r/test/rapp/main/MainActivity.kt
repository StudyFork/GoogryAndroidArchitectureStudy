package r.test.rapp.main

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import r.test.rapp.R
import r.test.rapp.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var progress: ProgressDialog
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
        bindViewModel(genMainViewModel())
        showKeyPad()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }

    private fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    private fun hideKeyPad() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.top.edtInput.windowToken, 0)
    }

    private fun showKeyPad() {
        binding.top.edtInput.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    private fun initView() {
        progress = ProgressDialog(this)
        lv_contents.emptyView = txt_empty

        val adt = MovieAdapter()
        lv_contents.adapter = adt
        lv_contents.onItemClickListener = OnItemClickListenerImpl(adt)
    }

    private fun bindViewModel(vm: MainViewModel) {
        vm.movies.observe(this, Observer {
            val inputList = vm.movies.value ?: return@Observer

            val adt = lv_contents.adapter as MovieAdapter
            val movieList = adt.getMovieList()
            movieList.clear()
            movieList.addAll(inputList)
            adt.notifyDataSetChanged()
        })

        vm.toastMsg.observe(this, Observer {
            val msg = vm.toastMsg.value ?: return@Observer
            showToast(msg)
        })

        vm.isLoading.observe(this, Observer {
            val showLoding = vm.isLoading.value ?: return@Observer
            if (showLoding)
                progress.show()
            else
                progress.hide()
        })

        vm.hideKeypad.observe(this, Observer {
            hideKeyPad()
        })
    }

    private fun genMainViewModel(): MainViewModel {

        val provider = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(applicationContext.resources) as T
            }
        })

        var vm: MainViewModel = provider.get(MainViewModel::class.java)
        binding.vm = vm;
        binding.lifecycleOwner = this
        return vm
    }

    private class OnItemClickListenerImpl(private val adt: MovieAdapter) :
        AdapterView.OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            val uri: Uri = Uri.parse(adt.getMovieList()[position].link)
            parent.context.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

}