package com.example.kotlinapplication.contract

import com.example.kotlinapplication.model.repository.DataRepositoryImpl
import io.reactivex.functions.Consumer

class Presenter(listener: Contract.View) : Contract.Presenter {
    private var view: Contract.View = listener
    private var dataRepositoryImpl: DataRepositoryImpl = DataRepositoryImpl()

    override fun loadData(type: String?, query: String) {
        when (type) {
            "영화" -> dataRepositoryImpl.callMovieResources(query).subscribe(
                Consumer { list ->
                    view.resultMovie(list.items)
                },
                Consumer { errorMessage ->
                    view.resultError("error 에러" + errorMessage)
                })

            "이미지" -> dataRepositoryImpl.callImageResources(query).subscribe(
                Consumer { list ->
                    view.resultImage(list.items)
                },
                Consumer { errorMessage ->
                    view.resultError("error 에러" + errorMessage)
                })
            "블로그" -> dataRepositoryImpl.callBlogResources(query).subscribe(
                Consumer { list ->
                    view.resultBlog(list.items)
                },
                Consumer { errorMessage ->
                    view.resultError("error 에러" + errorMessage)
                })
            "지식인" -> dataRepositoryImpl.callKinResources(query).subscribe(
                Consumer { list ->
                    view.resultKin(list.items)
                },
                Consumer { errorMessage ->
                    view.resultError("error 에러" + errorMessage)
                })
            else -> {
                view.resultError("에러")
            }
        }
    }

}