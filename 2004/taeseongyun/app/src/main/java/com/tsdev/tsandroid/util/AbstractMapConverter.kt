package com.tsdev.tsandroid.util

abstract class AbstractMapConverter<ITEM, OUT> {
    abstract fun toMap(params: ITEM): OUT
}