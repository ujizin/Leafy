package br.com.devlucasyuji.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.devlucasyuji.local.dao.AlarmDao
import br.com.devlucasyuji.local.dao.AlbumDao
import br.com.devlucasyuji.local.dao.PhotoDao
import br.com.devlucasyuji.local.model.AlarmEntity
import br.com.devlucasyuji.local.model.AlbumEntity
import br.com.devlucasyuji.local.model.PhotoEntity

/**
 * Database class.
 * */
@Database(
    entities = [
        AlarmEntity::class,
        AlbumEntity::class,
        PhotoEntity::class,
    ],
    version = 1,
)
abstract class Database : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    abstract fun albumDao(): AlbumDao

    abstract fun photoDao(): PhotoDao

    companion object {
        internal const val NAME = "camera_reminder_db"
    }
}
