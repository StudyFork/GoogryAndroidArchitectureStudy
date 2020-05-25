package com.example.architecture.activity.search.adapter

import com.example.architecture.util.ConstValue

class MovieHolderPresenter(
    private val view: MovieHolderContract.View
) : MovieHolderContract.Presenter {

    override fun removeMarkupTag(html: String): String {
        return html.replace("<b>", "").replace("</b>", "")
    }

    override fun checkImageUrl(imageUrl: String): String {
        return if (imageUrl.isBlank()) {
            ConstValue.NO_IMAGE_URL
        } else {
            imageUrl
        }
    }



}