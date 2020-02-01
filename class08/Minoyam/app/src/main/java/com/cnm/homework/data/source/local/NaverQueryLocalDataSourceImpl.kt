package com.cnm.homework.data.source.local

import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalEntity

class NaverQueryLocalDataSourceImpl(private val dao: LocalDao) : NaverQueryLocalDataSource {

    override fun saveCacheMovie(localEntity: LocalEntity) = dao.insertLocal(localEntity)

    override fun loadLocal(): List<LocalEntity> = dao.loadLocal()


}