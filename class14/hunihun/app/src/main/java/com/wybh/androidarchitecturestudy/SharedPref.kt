package com.wybh.androidarchitecturestudy

import android.content.Context
import android.content.Context.MODE_PRIVATE


object SharedPref {
    private const val KEY_MY_PREFERENCE = "com.wybh.shootingstar.PREFERENCE"
    private const val KEY_SAVE_SEARCH_WORD = "SAVE_SEARCH_WORD"

    fun setSaveSearchWord(userID: String, context: Context) {
        setPreference(KEY_SAVE_SEARCH_WORD, userID, context)
    }

    fun getSaveSearchWord(context: Context): String? {
        return getPrefString(KEY_SAVE_SEARCH_WORD, context)
    }

    // 데이터 저장 함수]
    private fun setPreference(key: String, value: Boolean, context: Context) {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun setPreference(key: String, value: String, context: Context) {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun setPreference(key: String, value: Int, context: Context) {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun setPreference(key: String, value: Float, context: Context) {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    private fun setPreference(key: String, value: Long, context: Context) {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putLong(key, value)
        editor.apply()
    }


    // 데이터 불러오기 함수
    private fun getPrefBoolean(key: String, context: Context): Boolean {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        return pref.getBoolean(key, false)
    }

    private fun getPrefString(key: String, context: Context): String? {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        return pref.getString(key, "")
    }

    private fun getPrefInt(key: String, context: Context): Int {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        return pref.getInt(key, 0)
    }

    private fun getPrefFloat(key: String, context: Context): Float {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        return pref.getFloat(key, 0f)
    }

    private fun getPrefLong(key: String, context: Context): Long {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        return pref.getLong(key, 0L)
    }

    // 데이터 한개씩 삭제하는 함수
    private fun setPrefRemove(key: String, context: Context) {
        val pref = context.getSharedPreferences(KEY_MY_PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove(key)
        editor.apply()
    }
}