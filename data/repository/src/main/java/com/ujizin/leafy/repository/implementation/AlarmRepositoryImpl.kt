package com.ujizin.leafy.repository.implementation

import com.ujizin.leafy.repository.datasource.AlarmDataSource
import com.ujizin.leafy.repository.mapper.AlarmMapper
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.repository.AlarmRepository
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
