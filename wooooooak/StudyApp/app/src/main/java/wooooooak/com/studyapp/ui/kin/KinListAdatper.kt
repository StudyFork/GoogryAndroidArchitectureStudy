package wooooooak.com.studyapp.ui.kin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_blog.view.description
import kotlinx.android.synthetic.main.item_image.view.title
import kotlinx.android.synthetic.main.item_kin.view.*
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class KinListAdapter(private val fragmentActivity: FragmentActivity) :
    BaseSearchListAdapter<Kin, KinListAdapter.KinViewHolder>(fragmentActivity, DiffCallback()) {

    private val naverApiRepository = NaverApiRepositoryImpl()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KinViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_kin, parent, false)
    )

    override fun bindItemViewHolder(holder: KinViewHolder, position: Int) {
        getItem(position)?.let { kin ->
            holder.bind(kin, View.OnClickListener {
                fragmentActivity.startWebView(kin.link)
            })
        }
    }

    override suspend fun initItemListByTitleAsync(title: String) =
        naverApiRepository.getKinList(title, null)

    override suspend fun getMoreItemListFromStartIndexAsync(title: String, startIndex: Int) =
        naverApiRepository.getKinList(title, startIndex)

    inner class KinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(kin: Kin, onClickItem: View.OnClickListener) {
            with(itemView) {
                title.text = HtmlCompat.fromHtml(kin.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                description.text = HtmlCompat.fromHtml(kin.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                kin_card.setOnClickListener(onClickItem)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Kin>() {
    override fun areItemsTheSame(oldItem: Kin, newItem: Kin) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Kin, newItem: Kin) =
        oldItem == newItem
}