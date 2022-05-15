package br.com.devlucasyuji.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Alarm [Entity]
 *
 * @param id the alarm id
 * @param ring the ring's alarm
 * @param photoId the photo id linked to the alarm
 * */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = PhotoEntity::class,
            parentColumns = ["photo_id"],
            childColumns = ["photo_id"],
            onDelete = ForeignKey.SET_DEFAULT
        )
    ]
)
internal data class AlarmEntity(
    @ColumnInfo(name = "alarm_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "alarm_ring")
    val ring: String,
    @ColumnInfo(name = "photo_id")
    val photoId: Long,
)
