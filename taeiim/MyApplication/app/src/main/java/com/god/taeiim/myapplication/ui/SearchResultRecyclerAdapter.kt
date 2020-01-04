package com.god.taeiim.myapplication.ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.base.BaseRecyclerAdapter
import com.god.taeiim.myapplication.base.BaseViewHolder

class SearchResultRecyclerAdapter<ITEM : Any, B : ViewDataBinding>(
    private val tab: Tabs,
    layout: Int,
    bindingVariableId: Int
) : BaseRecyclerAdapter<ITEM, B>(layout, bindingVariableId) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> {

        return BaseViewHolder(
            layoutResId,
            parent = parent,
            bindingVariableId = bindingVariableId
        )

//        return when (viewType) {
//            Tabs.BLOG.ordinal -> BlogViewHolder(parent) as BaseViewHolder<B>
//            Tabs.NEWS.ordinal -> NewsViewHolder(view)
//            Tabs.MOVIE.ordinal -> MovieViewHolder(view)
//            Tabs.BOOK.ordinal -> BookViewHolder(view)
//            else -> throw IllegalArgumentException()
//        }
    }

    override fun getItemViewType(position: Int): Int {
        return tab.ordinal
    }

    /*
    inner class BlogViewHolder(parent: ViewGroup) : BaseViewHolder<ItemContentsBinding>(
        layoutResId,
        parent = parent,
        bindingVariableId = bindingVariableId
    ) {
        override fun onBindViewHolder(item: Any?) {
            super.onBindViewHolder(item)
            binding.item!!.subtitle = binding.item!!.postdate
            Log.d("aldskjflsdj subtitle : ", binding.item!!.subtitle?:"null")
            Log.d("aldskjflsdj postdate : ", binding.item!!.postdate?:"null")
        }
    }
*/

/*
    inner class NewsViewHolder(itemView: View) :
        SearchResultBaseViewHolder<SearchResult.Item>(itemView) {
        override fun bind(item: SearchResult.Item) {
            item.commonBind()
            binding.subTitleTv.visibility = View.GONE
        }
    }

    inner class MovieViewHolder(itemView: View) :
        SearchResultBaseViewHolder<SearchResult.Item>(itemView) {
        override fun bind(item: SearchResult.Item) {
            itemView.setImage(item)
            with(binding) {
                item.commonBind()
                subTitleTv.text = item.pubDate.fromHtml()
                descTv.text = (item.director + item.actor).fromHtml()
            }
        }
    }

    inner class BookViewHolder(itemView: View) :
        SearchResultBaseViewHolder<SearchResult.Item>(itemView) {
        override fun bind(item: SearchResult.Item) {
            item.commonBind()
            itemView.setImage(item)
//            itemView.subTitleTv.text = item.author.fromHtml()
        }
    }
*/

//    abstract class SearchResultBaseViewHolder<T>(itemView: View) :
//        BaseViewHolder<ItemContentsBinding>(R.layout.item_contents, itemView.parent, BR.item) {
//        private var link: String? = ""
//
//        abstract fun bind(item: T)
//
//        onCre
//        fun SearchResult.Item.commonBind() {
//            with(binding) {
//                titleTv.text = title.fromHtml()
//                descTv.text = description.fromHtml()
//            }
//            this@SearchResultBaseViewHolder.link = link
//        }
//
//        init {
//            itemView.setOnClickListener {
//                startActivity(
//                    itemView.context,
//                    Intent(Intent.ACTION_VIEW, Uri.parse(link)),
//                    null
//                )
//            }
//        }
//    }
}

