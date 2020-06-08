package r.test.rapp.main

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import kotlinx.android.synthetic.main.activity_main.*
import r.test.rapp.BR
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var progress: ProgressDialog
    private  var vm: MainViewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm;

        initView()
        bindViewModel()
        showKeyPad()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }

    override fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    override fun showProgress() {
        progress.show()
    }

    override fun hideProgress() {
        progress.hide()
    }

    override fun hideKeyPad() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.top.edtInput.windowToken, 0)
    }

    override fun showKeyPad() {
        binding.top.edtInput.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    override fun refreshListView(items: List<Item>) {
        val adt = lv_contents.adapter as MovieAdapter
        val movieList = adt.getMovieList();
        movieList.clear()
        movieList.addAll(items)
        adt.notifyDataSetChanged()
    }

    private fun initView() {
        progress = ProgressDialog(this)
        lv_contents.emptyView = txt_empty

        val adt = MovieAdapter(vm)
        lv_contents.adapter = adt
        lv_contents.onItemClickListener = OnItemClickListenerImpl(adt)
    }

    private fun bindViewModel() {
        vm.toastMsg.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback() {

            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val msg = vm.toastMsg.get()  ?: return
                showToast(msg)
            }
        })

        vm.toastRes.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val msgId = vm.toastRes.get()  ?: return
                showToast(msgId)
            }
        })

        vm.isLoading.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d("#####", "isisis " + (propertyId === BR.vm))
                val showLoding = vm.isLoading.get()  ?: return
                if(showLoding)
                    progress.show()
                else
                    progress.hide()
            }
        })

        vm.showKeypad.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val showKeypad = vm.showKeypad.get()  ?: return
                if(showKeypad)
                    showKeyPad()
                else
                    hideKeyPad()
            }
        })

    }

    private class OnItemClickListenerImpl(private val adt: MovieAdapter) : AdapterView.OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            val uri: Uri = Uri.parse(adt.getMovieList()[position].link)
            parent.context.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

}