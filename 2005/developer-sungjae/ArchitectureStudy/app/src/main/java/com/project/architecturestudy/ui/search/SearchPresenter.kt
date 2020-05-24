package com.project.architecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.project.architecturestudy.data.repository.NaverMovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchPresenter(
    private val view: SearchContract.View,
    private val naverMovieRepository: NaverMovieRepository
) : SearchContract.Presenter {

    override val adapter = SearchAdapter()

    override fun validateSearchWord(searchWord: String) {
        when (searchWord.isNotEmpty()) {

            true -> getMovieListFromRemote(searchWord)
            false -> view.showSearchWordIsEmpty()
        }
    }


    override fun getMovieListFromLocal() {
        naverMovieRepository.getCashedMovieList(
            onSuccess = {
                adapter.setLocalMovieData(it)
            },
            onFailure = {
                Log.d("bsjbsj", "Throwable:$it")
            })
    }

    override fun getMovieListFromRemote(searchWord: String) {
        naverMovieRepository.getMovieList(searchWord,
            onGetRemoteData = { single ->
                single.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {

                            adapter.setRemoteMovieData(it.items)
                            view.showRemoteDataSuccess()
                        }, { t ->

                            view.showRemoteDataFailure()
                            Log.d("bsjbsj", t.toString())
                        })

            })
    }

    override fun setItemClickListener() {
        adapter.onClick = { item ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            view.moveWebMovieDetailPage(intent)
        }
    }

    override fun remoteDispose() {
        naverMovieRepository.dispose()
    }
}