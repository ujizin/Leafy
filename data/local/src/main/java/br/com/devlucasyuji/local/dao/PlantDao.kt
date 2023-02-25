package br.com.devlucasyuji.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.devlucasyuji.local.model.PlantEntity

/**
 * [Dao] class to handle with [PlantEntity].
 * */
@Dao
interface PlantDao {

    /**
     * Get all plant entity.
     *
     * @return list of [PlantEntity]
     * */
    @Query("SELECT * FROM plant")
    suspend fun getAll(): List<PlantEntity>

    @Query("SELECT * FROM plant where plant_id=:id")
    suspend fun findById(id: Long): PlantEntity?

    /**
     * Find all plant entity by album id
     *
     * @param albumId the album id linked to [PlantEntity]
     * @return list of [PlantEntity]
     * */
    @Query("SELECT * FROM plant WHERE album_id = :albumId")
    suspend fun findByAlbumId(albumId: Long): List<PlantEntity>

    /**
     * Insert a new [PlantEntity].
     *
     * @return return the new plant's id
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: PlantEntity): Long


    /**
     * Insert news [PlantEntity].
     *
     * */
    @Insert
    suspend fun insertAll(vararg plant: PlantEntity)

    /**
     * Update a [PlantEntity] passed on parameter.
     *
     * @param plant [PlantEntity] to be updated
     * */
    @Update
    suspend fun update(plant: PlantEntity)

    /**
     * Delete a [PlantEntity] passed on parameter.
     *
     * @param plant [PlantEntity] to be deleted
     * */
    @Delete
    suspend fun delete(plant: PlantEntity)
}
