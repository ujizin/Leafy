package com.ujizin.leafy.alarm.model

import androidx.annotation.StringRes
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.features.alarm.R

sealed class RepeatMode(
    @StringRes val display: Int,
    val weekDays: List<WeekDay>
) {
    object Daily : RepeatMode(R.string.daily, WeekDay.values().toList())

    object MonToFriday : RepeatMode(
        R.string.mon_to_friday,
        WeekDay.values().filter { it != WeekDay.Saturday && it != WeekDay.Sunday },
    )

    data class Custom(val customWeekDays: List<WeekDay>) :
        RepeatMode(R.string.custom, customWeekDays)


    companion object {
        fun getValues(customWeekDays: List<WeekDay> = emptyList()): List<RepeatMode> = listOf(
            Daily, MonToFriday, Custom(customWeekDays)
        )
    }
}

fun RepeatMode.asCustom(): RepeatMode.Custom? = this as? RepeatMode.Custom