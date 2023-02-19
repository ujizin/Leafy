package br.com.devlucasyuji.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.devlucasyuji.local.dao.AlarmDao
import br.com.devlucasyuji.local.dao.AlbumDao
import br.com.devlucasyuji.local.dao.PlantMemoryDao
import br.com.devlucasyuji.local.model.AlarmEntity
import br.com.devlucasyuji.local.model.AlbumEntity
import br.com.devlucasyuji.local.model.PlantEntity

/**
 * Memory database
 * */
@Database(
    entities = [
        PlantEntity::class,
        AlarmEntity::class,
        AlbumEntity::class
    ],
    version = 1,
)
abstract class MemoryDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    abstract fun albumDao(): AlbumDao

    abstract fun plantDao(): PlantMemoryDao
}
