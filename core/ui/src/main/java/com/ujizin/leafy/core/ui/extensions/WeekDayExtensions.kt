package com.ujizin.leafy.core.ui.extensions

import android.content.Context
import androidx.annotation.StringRes
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.domain.model.WeekDay

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

fun WeekDay.getShortDay(
    context: Context,
) = context.getString(displayNameRes).substring(0, 3)

fun List<WeekDay>.getDisplayName(context: Context): String {
    if (isEmpty()) return String.Empty

    if (size == WeekDay.values().size) {
        return context.getString(R.string.daily)
    }

    var shortName = context.getString(first().displayNameRes)
    var longName = ""

    var useShortName = true
    var nextOrdinal = first().ordinal

    forEachIndexed { index, day ->

        if (nextOrdinal != day.ordinal) {
            useShortName = false
        }

        if (index == lastIndex) {
            shortName = context.getString(
                R.string.day_to_day,
                shortName,
                context.getString(day.displayNameRes),
            )
        }

        longName += "${if (index == 0) "" else ", "} ${day.getShortDay(context)}"
        nextOrdinal += 1
    }

    return if (useShortName) shortName else longName
}
