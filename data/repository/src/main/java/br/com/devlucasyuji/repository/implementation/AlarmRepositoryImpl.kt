package br.com.devlucasyuji.repository.implementation

import br.com.devlucasyuji.domain.model.Alarm
import br.com.devlucasyuji.domain.repository.AlarmRepository
import br.com.devlucasyuji.repository.datasource.AlarmDataSource
import br.com.devlucasyuji.repository.mapper.AlarmMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AlarmRepositoryImpl(
    private val dataSource: AlarmDataSource,
    private val mapper: AlarmMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlarmRepository {

    override fun insertAlarm(alarm: Alarm) = flow {
        emit(dataSource.insertAlarm(mapper.toRepo(alarm)))
    }.flowOn(dispatcher)

}
