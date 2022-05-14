package br.com.devlucasyuji.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devlucasyuji.repository.model.Photo

/**
 * Album [Entity]
 *
 * @param id the album id
 * @param photos the photos' album
 */
@Entity
data class AlbumEntity(
    @ColumnInfo(name = "album_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "album_photos")
    val photos: List<Photo> = emptyList()
)