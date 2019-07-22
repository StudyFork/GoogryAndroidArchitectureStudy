package study.architecture.myarchitecture.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker

@Database(entities = [UpbitMarket::class, UpbitTicker::class], version = 1)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun getUpbitDao(): UpbitDao

    companion object {

        private var INSTANCE: LocalDataSource? = null

        fun getInstance(context: Context): LocalDataSource {

            if (INSTANCE == null) {
                synchronized(LocalDataSource::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDataSource::class.java,
                        "upbit.db"
                    )
                        //테스트용 : 빌드 시 마다 기존 데이터베이스 삭제
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE!!
        }

    }
}
