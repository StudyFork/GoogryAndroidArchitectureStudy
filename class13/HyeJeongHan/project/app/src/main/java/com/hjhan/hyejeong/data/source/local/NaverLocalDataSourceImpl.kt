package com.hjhan.hyejeong.data.source.local

class NaverLocalDataSourceImpl : NaverLocalDataSource {

    override fun saveQuery(query: String) = SharedPreferencesUtil.setQueryList(query)

    override fun getQueryList(): List<String> = SharedPreferencesUtil.getQueryList()

}