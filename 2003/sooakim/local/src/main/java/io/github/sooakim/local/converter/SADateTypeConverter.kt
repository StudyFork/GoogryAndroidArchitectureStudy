package io.github.sooakim.local.converter

import androidx.room.TypeConverter
import java.util.Date

internal object SADateTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    @JvmStatic
    fun toDate(time: Long): Date {
        return Date(time)
    }
}