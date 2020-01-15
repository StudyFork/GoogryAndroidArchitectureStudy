package com.cnm.homework.data.source.local

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalDatabase
import com.cnm.homework.data.source.local.db.LocalEntity
import io.reactivex.Single

class NaverQueryLoclDataSourceImpl(context: Context) : NaverQueryLocalDataSource {


    private val localDao: LocalDao by lazy {
        val db = LocalDatabase.getInstance(context) ?: throw NullPointerException(" db problem")
        db.localDao()
    }

    override fun saveCacheMovie(localEntity: LocalEntity): Single<Unit> {
        return Single.fromCallable { localDao.insertLocal(localEntity) }
    }
    override fun loadLocal(): LiveData<List<LocalEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}