package com.ujizin.leafy.core.ui.extensions

import com.ujizin.leafy.domain.model.Alarm

val Alarm.dateFormatted: String
    get() = "${hours.toDecimalFormat()}:${minutes.toDecimalFormat()}"
