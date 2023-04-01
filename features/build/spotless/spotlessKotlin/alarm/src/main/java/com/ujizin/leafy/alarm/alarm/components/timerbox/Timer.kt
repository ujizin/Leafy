package com.ujizin.leafy.alarm.alarm.components.timerbox

import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("00")

internal fun List<Int>.toDecimalFormat() = map { it.toDecimalFormat() }

internal fun Int?.toDecimalFormat() = this?.let { decimalFormat.format(it) } ?: "00"

internal enum class TimeUnit(val numbers: List<String>) {
    Hour((0..23).toList().toDecimalFormat()),
    Minutes((0..59).toList().toDecimalFormat()),
}
