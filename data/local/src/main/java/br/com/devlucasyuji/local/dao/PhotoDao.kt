package br.com.devlucasyuji.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.devlucasyuji.local.model.PhotoEntity

/**
 * [Dao] class to handle with [PhotoEntity].
 * */
@Dao
interface PhotoDao {

    /**
     * Get all photo entity.
     *
     * @return list of [PhotoEntity]
     * */
    @Query("SELECT * FROM photo")
    suspend fun getAll(): List<PhotoEntity>

    /**
     * Find all photo entity by album id
     *
     * @param albumId the album id linked to [PhotoEntity]
     * */
    @Query("SELECT * FROM photo WHERE album_id = :albumId")
    suspend fun findByAlbumId(albumId: Long): List<PhotoEntity>

    /**
     * Insert a new [PhotoEntity].
     *
     * */
    @Insert
    suspend fun insert(vararg photo: PhotoEntity)

    /**
     * Update a [PhotoEntity] passed on parameter.
     *
     * @param photo [PhotoEntity] to be updated
     * */
    @Update
    suspend fun update(photo: PhotoEntity)

    /**
     * Delete a [PhotoEntity] passed on parameter.
     *
     * @param photo [PhotoEntity] to be deleted
     * */
    @Delete
    suspend fun delete(photo: PhotoEntity)
}