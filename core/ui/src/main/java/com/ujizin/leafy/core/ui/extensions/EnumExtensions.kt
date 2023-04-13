package com.ujizin.leafy.core.ui.extensions

fun <T : Enum<T>> List<T>.isOrdinalConsecutive(): Boolean {
    var prevOrdinal = -1
    for (enumValue in this) {
        if (prevOrdinal != -1 && enumValue.ordinal != prevOrdinal + 1) {
            return false
        }
        prevOrdinal = enumValue.ordinal
    }
    return true
}