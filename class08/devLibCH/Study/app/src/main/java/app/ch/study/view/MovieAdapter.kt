package app.ch.study.view

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import app.ch.study.R
import app.ch.study.data.remote.response.MovieModel
import com.bumptech.glide.Glide

class MovieAdapter(
    private val context: Context,
    private val list: ArrayList<MovieModel>,
    private val listener: EventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun replaceAll(items: ArrayList<MovieModel>) {
        items.let {
            this.list.run {
                clear()
                addAll(it)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieViewHolder(parent.context, R.layout.list_item_movie, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MovieViewHolder).onBindViewHolder(list[position])

    inner class MovieViewHolder(
        context: Context,
        view: Int,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(context).inflate(view, parent, false)
    ) {

        fun onBindViewHolder(item: MovieModel) {
            val itemParent: ConstraintLayout = itemView.findViewById(R.id.v_item)
            val ivPoster: ImageView = itemView.findViewById(R.id.iv_poster)
            val tvName: TextView = itemView.findViewById(R.id.tv_name)
            val rbUserRating: RatingBar = itemView.findViewById(R.id.rb_user_rating)
            val tvPubDate: TextView = itemView.findViewById(R.id.tv_pub_date)
            val tvDirector: TextView = itemView.findViewById(R.id.tv_director)
            val tvActor: TextView = itemView.findViewById(R.id.tv_actor)

            tvName.text = fromHtml(item.title)
            rbUserRating.rating = item.userRating / 2F
            tvPubDate.text = item.pubDate
            tvDirector.text = item.director
            tvActor.text = item.actor

            Glide
                .with(context)
                .load(item.image)
                .centerCrop()
                .into(ivPoster)

            itemParent.setOnClickListener {
                listener.itemClick(item.link)
            }
        }

        private fun fromHtml(source: String): Spanned {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                Html.fromHtml(source)
            else
                Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    interface EventListener {
        fun itemClick(url: String)
    }

}