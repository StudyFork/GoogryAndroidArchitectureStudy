package com.cnm.homework.data.source.local

import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalEntity
import io.reactivex.Single

class NaverQueryLocalDataSourceImpl(localDao: LocalDao) : NaverQueryLocalDataSource {

    private val dao = localDao
    override fun saveCacheMovie(localEntity: LocalEntity): Single<Unit> {
        return Single.fromCallable { dao.insertLocal(localEntity) }
    }

    override fun loadLocal(): List<LocalEntity> {
        return dao.loadLocal()
    }

}