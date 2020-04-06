package com.example.kangraemin.util

import android.view.View
import android.widget.*
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageFromUrl")
fun setImageFromUrl(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .centerCrop()
        .into(view)
}

@BindingAdapter("rating")
fun setRating(ratingBar: RatingBar, rating: String) {
    ratingBar.rating = (rating.toFloatOrNull() ?: 0f) / 2
}

@BindingAdapter("fromHtml")
fun transferFromHtml(textView: TextView, htmlString: String) {
    textView.text = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("activateButtonByPresenceOfString")
fun activateButtonByPresenceOfString(button: Button, enteredText: String) {
    if (enteredText.isEmpty()) {
        button.alpha = 0.3f
        button.isEnabled = false
    } else {
        button.alpha = 1f
        button.isEnabled = true
    }
}

@BindingAdapter("activateLoginButton")
fun activateLoginButton(button: Button, loginInfoEntered: Boolean) {
    if (loginInfoEntered) {
        button.alpha = 1f
        button.isEnabled = true
    } else {
        button.alpha = 0.5f
        button.isEnabled = false
    }
}

@BindingAdapter("onFocusChange")
fun onFocusChange(text: EditText, listener: View.OnFocusChangeListener?) {
    text.onFocusChangeListener = listener
}

@BindingAdapter("activateLogOutButton")
fun activateLogOutButton(button: Button, activate: Boolean) {
    if (activate) {
        button.visibility = View.VISIBLE
    } else {
        button.visibility = View.GONE
    }
}