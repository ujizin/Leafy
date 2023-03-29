package com.ujizin.leafy.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ujizin.leafy.local.dao.AlarmDao
import com.ujizin.leafy.local.dao.AlbumDao
import com.ujizin.leafy.local.dao.PlantMemoryDao
import com.ujizin.leafy.local.model.AlarmEntity
import com.ujizin.leafy.local.model.AlbumEntity
import com.ujizin.leafy.local.model.PlantEntity

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
