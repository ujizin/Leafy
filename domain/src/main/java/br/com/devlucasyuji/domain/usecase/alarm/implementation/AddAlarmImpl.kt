package br.com.devlucasyuji.domain.usecase.alarm.implementation

import br.com.devlucasyuji.domain.model.Alarm
import br.com.devlucasyuji.domain.repository.AlarmRepository
import br.com.devlucasyuji.domain.usecase.alarm.AddAlarm
import kotlinx.coroutines.flow.Flow

internal class AddAlarmImpl(
    private val alarmRepository: AlarmRepository,
) : AddAlarm {

    override fun invoke(alarm: Alarm): Flow<Unit> = alarmRepository.insertAlarm(alarm)

}