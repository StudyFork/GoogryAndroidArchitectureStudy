package sample.nackun.com.studyfirst.Base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

abstract class BaseFragment(
    @LayoutRes private val layoutRes: Int
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(layoutRes, container, false)

    fun showToast(msg: String) =
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
}