package com.example.archstudy.data.repository

import android.os.AsyncTask
import android.util.Log
import com.example.archstudy.MovieDataResponse
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.data.source.local.NaverQueryLocalDataSourceImpl
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSourceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverQueryRepositoryImpl(
    private val naverQueryLocalDataSource: NaverQueryLocalDataSourceImpl,
    private val naverQueryRemoteDataSource: NaverQueryRemoteDataSourceImpl
) : NaverQueryRepository {

    override fun requestRemoteData(
        query: String,
        successCallback: (MutableList<MovieData>) -> Unit,
        failCallback: (Throwable) -> Unit
    ) {

        naverQueryRemoteDataSource
            .getMovie(query)
            .apply {
                this.enqueue(object : Callback<MovieDataResponse> {

                    override fun onFailure(call: Call<MovieDataResponse>, t: Throwable) {
                        failCallback(t)
                    }

                    override fun onResponse(
                        call: Call<MovieDataResponse>,
                        response: Response<MovieDataResponse>
                    ) {
                        if (response.body() != null) {
                            val items = response.body()?.items as MutableList<MovieData>
                            InsertLocalDataAsync(
                                query,
                                items
                            ).execute() // Remote Data 전송 성공 시 해당 Data를 Local에 저장
                            Log.d(
                                "Async", "onResponse().query : $query, onResponse().items : $items"
                            )
                            successCallback(items)
                        }
                    }
                })
            }
    }

    override fun requestLocalData(
        query: String
    ): MutableList<MovieData>? {
        return naverQueryLocalDataSource.requestLocalData(query)
    }

    override fun insertLocalData(query: String, data: List<MovieData>) {
        Log.d("Async", "insertLocalData.data $data")
        naverQueryLocalDataSource.insertLocalData(query, data.toMutableList())
    }

    inner class RequestLocalDataAsync(
        private var query: String,
        private var asyncTaskDataListener: AsyncTaskDataListener
    ) : AsyncTask<Unit, Unit, MutableList<MovieData>>() {

        override fun doInBackground(vararg param: Unit?): MutableList<MovieData>? {
            Log.d("Async", "RequestLocalDataAsync.doInBackground()")
            var result = requestLocalData(query)
            Log.d("Async", "doInBackground().result : $result")
            return result
        }

        override fun onPostExecute(result: MutableList<MovieData>?) {
            super.onPostExecute(result)
            Log.d("Async", "Request Result : $result")
            asyncTaskDataListener.onResult(result!!)
        }

    }


    inner class RequestLocalQueryAsync(private var asyncTaskQueryListener: AsyncTaskQueryListener) :
        AsyncTask<Unit, Unit, String>() {

        override fun doInBackground(vararg p0: Unit?): String {
            Log.d("Async", "RequestLocalQueryAsync.doInBackground()")
            val result = naverQueryLocalDataSource.requestSearchWord()
            Log.d("Async", "Request Result : $result")
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            result?.let {
                asyncTaskQueryListener.onResult(result)
            }
        }
    }

    inner class InsertLocalDataAsync(
        private var query: String,
        private var data: List<MovieData>
    ) : AsyncTask<MutableList<MovieData>, Unit, Unit>() {

        override fun doInBackground(vararg p0: MutableList<MovieData>?) {
            Log.d("Async", "InsertLocalDataAsync.doInBackground()")
            Log.d("Async", "InsertLocalDataAsync.query : $query")
            Log.d("Async", "InsertLocalDataAsync.data  : $data")
            naverQueryLocalDataSource.insertLocalData(query, data as MutableList<MovieData>)
        }
    }

    interface AsyncTaskDataListener {
        fun onResult(result: MutableList<MovieData>)
    }

    interface AsyncTaskQueryListener {
        fun onResult(result: String)
    }
}