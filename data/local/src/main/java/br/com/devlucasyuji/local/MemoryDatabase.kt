package br.com.devlucasyuji.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.devlucasyuji.local.dao.PlantMemoryDao
import br.com.devlucasyuji.local.model.PlantEntity

/**
 * Memory database
 * */
@Database(
    entities = [PlantEntity::class],
    version = 1,
)
abstract class MemoryDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantMemoryDao
}
