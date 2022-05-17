package br.com.devlucasyuji.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Photo [Entity]
 *
 * @param id the photo id
 * @param title the photo title
 * @param date the photo date
 * @param filePath file path of the photo
 * @param description the photo description
 * @param albumId the album id linked to the photo
 * */
@Entity(
    tableName = "photo",
    foreignKeys = [
        ForeignKey(
            entity = AlbumEntity::class,
            parentColumns = ["album_id"],
            childColumns = ["album_id"],
            onDelete = ForeignKey.SET_DEFAULT
        )
    ]
)
data class PhotoEntity(
    @ColumnInfo(name = "photo_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "photo_title")
    val title: String,
    @ColumnInfo(name = "photo_date")
    val date: String,
    @ColumnInfo(name = "photo_file_path")
    val filePath: String,
    @ColumnInfo(name = "photo_description")
    val description: String,
    @ColumnInfo(name = "photo_favorite")
    val favorite: Boolean,
    @ColumnInfo(name = "album_id")
    val albumId: Long? = null,
)
