package br.com.devlucasyuji.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.devlucasyuji.local.model.PlantEntity


/**
 * Dao class to handle with [PlantEntity] in memory.
 * */
interface PlantMemoryDao {

    /**
     *
     * */
    @Query("SELECT * FROM plant where plant_id= :plantId LIMIT 1")
    suspend fun getById(plantId: Long): PlantEntity?

    /**
     * Insert a new [PlantEntity].
     *
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg plant: PlantEntity)

    /**
     * Update a [PlantEntity] passed on parameter.
     *
     * @param plant [PlantEntity] to be updated
     * */
    @Update
    suspend fun update(plant: PlantEntity)

}