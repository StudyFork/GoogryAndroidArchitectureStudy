package com.namjackson.archstudy.util

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.view.coinlist.adapter.CoinListAdapter


@BindingAdapter("coinItems")
fun RecyclerView.setCoinItem(list: List<Ticker>?) {
    if (list != null) {
        (adapter as? CoinListAdapter)?.setList(list)
    }
}

@BindingAdapter("android:visibility")
fun View.setVisibility(boolean: Boolean) {
    visibility = if (boolean) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
fun bindSpinnerData(spinner: AppCompatSpinner, newSelectedValue: String?, newTextAttrChanged: InverseBindingListener) {

    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
            newTextAttrChanged.onChange()
        }

        override fun onNothingSelected(adapterView: AdapterView<*>) {
        }
    }
    if (newSelectedValue != null) {
        val pos = (spinner.adapter as? ArrayAdapter<String>)?.getPosition(newSelectedValue)
        spinner.setSelection(pos ?: 0, true)
    }

}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun selectedValue(spinner: AppCompatSpinner): String {
    return spinner.selectedItem as String
}