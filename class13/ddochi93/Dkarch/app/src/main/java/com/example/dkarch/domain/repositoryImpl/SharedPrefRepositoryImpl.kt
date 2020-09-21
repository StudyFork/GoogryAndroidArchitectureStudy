package com.example.dkarch.domain.repositoryImpl

import android.content.Context
import com.example.dkarch.domain.repository.SharedPrefRepository
import com.example.dkarch.domain.sharedpref.SharedPreferenceHelper
import rx.Observable


class SharedPrefRepositoryImpl(context: Context) : SharedPrefRepository {
    private val instance = SharedPreferenceHelper.getInstance(context)

    override fun writePrefs(key: String?) {
        instance.writePrefs(key)
    }

    override fun writePrefs(key: String?, value: Int?) {
        instance.writePrefs(key, value)
    }

    override fun writePrefs(key: String?, value: Long?) {
        instance.writePrefs(key, value)
    }

    override fun writePrefs(key: String?, value: String?) {
        instance.writePrefs(key, value)
    }

    override fun writePrefs(key: String?, value: Boolean) {
        instance.writePrefs(key, value)
    }

    override fun getPrefsBooleanValue(key: String?): Boolean {
        return instance.getPrefsBooleanValue(key)
    }

    override fun getPrefsBooleanValue(key: String?, defaultValue: Boolean): Boolean {
        return instance.getPrefsBooleanValue(key, defaultValue)
    }

    override fun resetPrefsIntValue(key: String?) {
        instance.resetPrefsIntValue(key)
    }

    override fun getPrefsIntValue(key: String?): Int {
        return instance.getPrefsIntValue(key)
    }

    override fun getPrefsIntValue(key: String?, defaultValue: Int?): Int {
        return instance.getPrefsIntValue(key, defaultValue)
    }

    override fun getPrefsLongValue(key: String?): Long {
        return instance.getPrefsLongValue(key)
    }

    override fun getPrefsStringValue(key: String?): String? {
        return instance.getPrefsStringValue(key)
    }

    override fun removePrefsValue(key: String?) {
        instance.removePrefsValue(key)
    }

    override fun getString(key: String?): Observable<String?>? {
        return instance.getString(key)
    }

    override fun getInteger(key: String?): Observable<Int?>? {
        return instance.getInteger(key)
    }
}
