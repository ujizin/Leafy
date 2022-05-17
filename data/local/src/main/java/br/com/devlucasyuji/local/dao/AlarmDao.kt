package br.com.devlucasyuji.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.devlucasyuji.local.model.AlarmEntity

/**
 * [Dao] class to handle with [AlarmEntity]
 * */
@Dao
interface AlarmDao {

    /**
     * Get all [AlarmEntity].
     *
     * @return list of [AlarmEntity]
     * */
    @Query("SELECT * FROM alarm")
    suspend fun getAll(): List<AlarmEntity>

    /**
     * Find all [AlarmEntity] by photo id.
     *
     * @param photoId photo id to find all [AlarmEntity]
     * @return list of [AlarmEntity]
     * */
    @Query("SELECT * FROM alarm WHERE photo_id = :photoId")
    suspend fun findByPhotoId(photoId: Long): List<AlarmEntity>?

    /**
     * Insert a new [AlarmEntity].
     *
     * @param alarm [AlarmEntity] to be added
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg alarm: AlarmEntity)

    /**
     * Update [AlarmEntity] passed on parameter.
     *
     * @param alarm [AlarmEntity] to be updated
     * */
    @Update
    suspend fun update(alarm: AlarmEntity)

    /**
     * Delete [AlarmEntity] passed on parameter.
     *
     * @param alarm [AlarmEntity] to be deleted
     * */
    @Delete
    suspend fun delete(alarm: AlarmEntity)
}