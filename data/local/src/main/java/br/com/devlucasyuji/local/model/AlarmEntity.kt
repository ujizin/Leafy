package br.com.devlucasyuji.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.devlucasyuji.repository.model.Photo

/**
 * Alarm [Entity]
 *
 * @param
 * */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Photo::class,
            parentColumns = ["photo_id"],
            childColumns = ["photo_id"],
            onDelete = ForeignKey.SET_DEFAULT
        )
    ]
)
data class AlarmEntity(
    @ColumnInfo(name = "alarm_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "alarm_ring")
    val ring: String,
    @ColumnInfo(name = "photo_id")
    val photoId: Long,
)
