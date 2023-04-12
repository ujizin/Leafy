package com.ujizin.leafy.alarm.extensions

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ujizin.leafy.alarm.ui.components.modal.ModalValue
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.features.alarm.R

val WeekDay.displayNameRes: Int
    @StringRes
    get() = when (this) {
        WeekDay.Sunday -> R.string.sunday
        WeekDay.Monday -> R.string.monday
        WeekDay.Tuesday -> R.string.tuesday
        WeekDay.Wednesday -> R.string.wednesday
        WeekDay.Thursday -> R.string.thursday
        WeekDay.Friday -> R.string.friday
        WeekDay.Saturday -> R.string.saturday
    }

@Composable
fun List<WeekDay>.mapToModalValue(): List<ModalValue<WeekDay>> = map {
    ModalValue(stringResource(it.displayNameRes), it)
}