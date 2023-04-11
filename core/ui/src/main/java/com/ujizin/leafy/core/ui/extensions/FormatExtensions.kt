package com.ujizin.leafy.core.ui.extensions

import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("00")

fun Int?.toDecimalFormat() = this?.let { decimalFormat.format(it) } ?: "00"