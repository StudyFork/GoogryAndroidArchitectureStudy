package r.test.rapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.databinding.RowContentBinding

/**
 * 리스트 뷰의 커스텀 어댑터.
 */
class MovieAdapter : BaseAdapter() {
    private val movieList: ArrayList<Item> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(parent?.context)
        val rowView: View = convertView ?: inflater.inflate(R.layout.row_content, parent, false)

        val holder = (convertView?.tag as? ViewHolder)
            ?: run {
                ViewHolder(rowView)
                    .also { rowView.tag = it }
            }

        val item = movieList[position]

        holder.binding.movie = item

        return holder.binding.root
    }

    fun getMovieList(): ArrayList<Item> {
        return movieList
    }

    private class ViewHolder(view: View) {
        val binding: RowContentBinding = RowContentBinding.bind(view)
    }

    override fun getItem(position: Int): Item {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movieList.size
    }
}