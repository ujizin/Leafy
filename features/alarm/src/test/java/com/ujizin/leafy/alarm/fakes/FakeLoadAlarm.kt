package com.ujizin.leafy.alarm.fakes

import com.ujizin.leafy.alarm.AlarmTestHelper.createAlarm
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.alarm.LoadAlarm
import kotlinx.coroutines.flow.flow

class FakeLoadAlarm(
    private val until: Int = 10,
    val alarms: MutableList<Alarm> = (0..until).map { createAlarm(id = it + 1L, plantId = it + 1L) }.toMutableList()
) : LoadAlarm {


    override fun invoke(id: Long) = flow {
        val result = alarms.find { it.id == id }?.let {
            Result.Success(it)
        } ?: Result.Error(Exception("Alarm with id: $id not found"))

        emit(result)
    }
}

