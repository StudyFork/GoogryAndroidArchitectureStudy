package com.example.seonoh.seonohapp
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(){
    private lateinit var progressDialog: ProgressLoadingDialog


    fun showLoading() {
        if (!::progressDialog.isInitialized) {
            progressDialog = ProgressLoadingDialog(activity!!)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
            progressDialog!!.setOnDismissListener {
                try {
                    Thread.interrupted() //강제 종료.
                } catch (e: Exception) {
                }
            }
            progressDialog!!.show()
        } else if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
        }
    }

    fun hideLoading() {
        //로딩 다이어로그 숨기기
        try {
            if (::progressDialog.isInitialized) progressDialog!!.dismiss()
        } catch (e: Exception) {
        }

    }

}