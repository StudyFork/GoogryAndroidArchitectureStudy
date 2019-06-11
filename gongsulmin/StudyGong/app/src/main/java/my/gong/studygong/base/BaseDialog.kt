package my.gong.studygong.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseDialog(private val layoutRes: Int) : androidx.fragment.app.DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpDialog()
    }

    private fun setUpDialog() {
        dialog.window!!.attributes.apply {
            width = resources.displayMetrics.widthPixels / 10 * 8
        }
    }
}