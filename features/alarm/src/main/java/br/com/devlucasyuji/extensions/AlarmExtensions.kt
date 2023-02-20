package br.com.devlucasyuji.extensions

import android.app.AlarmManager
import android.content.Context
import android.os.Build


/**
 * Get alarm manager instance from context
 * */
val Context.alarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

/**
 * Check if has permission in schedule alarms.
 * */
val AlarmManager.hasAlarmPermission
    get() = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> canScheduleExactAlarms()
        else -> true
    }