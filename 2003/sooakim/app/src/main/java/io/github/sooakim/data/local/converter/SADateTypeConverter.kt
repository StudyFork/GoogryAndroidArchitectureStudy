package io.github.sooakim.data.local.converter

import androidx.room.TypeConverter
import java.util.Date

object SADateTypeConverter {
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