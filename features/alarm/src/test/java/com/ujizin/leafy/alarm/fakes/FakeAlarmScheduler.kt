package com.ujizin.leafy.alarm.fakes

import android.os.Bundle
import com.ujizin.leafy.alarm.scheduler.AlarmScheduler

class FakeAlarmScheduler : AlarmScheduler {

    data class FakeAlarm(
        val type: Int,
        val hours: Int,
        val minutes: Int,
        val ringtoneUri: String,
        val bundle: Bundle,
    )

    private val _alarms = mutableListOf<FakeAlarm>()
    val alarms get() = _alarms.toList()

    override fun scheduleAlarm(
        type: Int,
        hours: Int,
        minutes: Int,
        ringtoneUri: String,
        bundle: Bundle,
    ) {
        _alarms += FakeAlarm(
            type = type,
            hours = hours,
            minutes = minutes,
            ringtoneUri = ringtoneUri,
            bundle = bundle,
        )
    }
}
