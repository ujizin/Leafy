package com.ujizin.leafy.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ujizin.leafy.local.dao.AlarmDao
import com.ujizin.leafy.local.dao.AlbumDao
import com.ujizin.leafy.local.dao.PlantDao
import com.ujizin.leafy.local.model.AlarmEntity
import com.ujizin.leafy.local.model.AlbumEntity
import com.ujizin.leafy.local.model.PlantEntity

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
