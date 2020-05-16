package com.eunice.eunicehong.ext

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.widget.SearchView
import androidx.databinding.BindingAdapter


@BindingAdapter("app:searchableInfo")
fun setSearchableInfo(view: SearchView, componentName: ComponentName) {
    val searchManager = view.context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
    view.setSearchableInfo(searchManager.getSearchableInfo(componentName))
}

@BindingAdapter("app:submitButtonEnabled")
fun setSubmitButtonEnabled(view: SearchView, value: Boolean) {
    view.isSubmitButtonEnabled = value

}


@BindingAdapter("app:queryRefinementEnabled")
fun setQueryRefinementEnabled(view: SearchView, value: Boolean) {
    view.isQueryRefinementEnabled = value
}


@BindingAdapter("app:setOnQueryTextListener")
fun setOnQueryTextListener(view: SearchView, onEmptyQuery: () -> Unit) {
    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return if (newText.isNullOrBlank()) {
                onEmptyQuery()
                true
            } else {
                false
            }
        }
    })
}