package br.com.devlucasyuji.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.devlucasyuji.local.model.AlbumEntity

/**
 * [Dao] class to handle with [AlbumEntity].
 * */
@Dao
interface AlbumDao {

    /**
     * Get all [AlbumEntity].
     *
     * @return list of [AlbumEntity]
     * */
    @Query("SELECT * FROM album")
    suspend fun getAll(): List<AlbumEntity>

    /**
     * Insert a new [AlbumEntity].
     *
     * @param album [AlbumEntity] to be added
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg album: AlbumEntity)

    /**
     * Update [AlbumEntity] passed on parameter.
     *
     * @param album [AlbumEntity] to be updated
     * */
    @Update
    suspend fun update(album: AlbumEntity)

    /**
     * Delete [AlbumEntity] passed on parameter.
     *
     * @param album [AlbumEntity] to be Deleted
     * */
    @Delete
    suspend fun delete(album: AlbumEntity)

}