package kr.dktsudgg.androidarchitecturestudy.view.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.dktsudgg.androidarchitecturestudy.R
import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem
import kr.dktsudgg.androidarchitecturestudy.view.adapter.MovieListAdapter
import kr.dktsudgg.androidarchitecturestudy.view.adapter.MovieSearchHistoryListAdapter

@BindingAdapter("bindImageUrlToImageView")
fun bindImageUrlToImageView(view: ImageView, imageUrl: String) {
    Glide
        .with(view.context)
        .load(imageUrl)
        .centerInside()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(view)
}

/**
 * 영화 검색 결과 데이터를 RecyclerView에 바인딩하는 로직 정의
 * (영화 검색결과 리스트 보여주는 RecyclerView에 어댑터 연결 및 목록 구분선 추가)
 */
@BindingAdapter("movieRecyclerViewItems")
fun bindMovieRecyclerViewItems(recyclerView: RecyclerView, items: List<MovieItem>?) {
    if (recyclerView.getAdapter() == null) {
        val adapter = MovieListAdapter()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.setAdapter(adapter);
    }

    items?.let {
        (recyclerView.adapter as MovieListAdapter).refreshData(it)
    }
}

/**
 * 영화 검색 이력 데이터를 RecyclerView에 바인딩하는 로직 정의
 * (영화 검색이력 리스트 보여주는 RecyclerView에 어댑터 연결 및 목록 구분선 추가)
 */
@BindingAdapter("searchHistoryRecyclerViewItems")
fun bindSearchHistoryRecyclerViewItems(recyclerView: RecyclerView, items: List<String>) {
    if (recyclerView.getAdapter() == null) {
        val adapter = MovieSearchHistoryListAdapter()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.setAdapter(adapter);
    }

    items?.let {
        (recyclerView.adapter as MovieSearchHistoryListAdapter).refreshData(it)
    }
}





