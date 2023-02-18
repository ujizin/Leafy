package br.com.devlucasyuji.repository.implementation

import android.media.RingtoneManager
import android.net.Uri
import br.com.devlucasyuji.domain.model.Ringtone
import br.com.devlucasyuji.domain.repository.RingtoneRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RingtoneRepositoryImpl(
    private val ringtoneManager: RingtoneManager,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RingtoneRepository {

    override fun getRingtones(): Flow<List<Ringtone>> = flow {
        ringtoneManager.setType(RingtoneManager.TYPE_RINGTONE)

        val cursor = ringtoneManager.cursor

        val ringtoneList = mutableListOf<Ringtone>()

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
                val uri = cursor.getString(RingtoneManager.URI_COLUMN_INDEX)
                val id = cursor.getString(RingtoneManager.ID_COLUMN_INDEX)

                ringtoneList.add(Ringtone(id, title, Uri.parse("$uri/$id")))
            } while (cursor.moveToNext())
        }

        cursor?.close()

        emit(ringtoneList)
    }.flowOn(dispatcher)
}