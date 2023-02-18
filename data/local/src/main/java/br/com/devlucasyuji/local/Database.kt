package br.com.devlucasyuji.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.devlucasyuji.local.dao.AlarmDao
import br.com.devlucasyuji.local.dao.AlbumDao
import br.com.devlucasyuji.local.dao.PlantDao
import br.com.devlucasyuji.local.model.AlarmEntity
import br.com.devlucasyuji.local.model.AlbumEntity
import br.com.devlucasyuji.local.model.PlantEntity

/**
 * Database class.
 * */
@Database(
    entities = [
        AlarmEntity::class,
        AlbumEntity::class,
        PlantEntity::class,
    ],
    version = 1,
)
abstract class Database : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    abstract fun albumDao(): AlbumDao

    abstract fun plantDao(): PlantDao

    companion object {
        internal const val NAME = "camera_reminder_db"
    }
}
