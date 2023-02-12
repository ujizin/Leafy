package br.com.devlucasyuji.alarm.components.alarm_box

import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("00")

fun List<Int>.toDecimalFormat() = map { decimalFormat.format(it) }

internal enum class Time(val numbers: List<String>) {
    Hour((1..23).toList().toDecimalFormat()),
    Minutes((0..59).toList().toDecimalFormat());
}
