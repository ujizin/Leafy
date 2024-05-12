package com.ujizin.leafy.alarm

import com.ujizin.leafy.core.ui.extensions.getNearestDay
import com.ujizin.leafy.domain.model.WeekDay
import org.junit.Assert.assertEquals
import org.junit.Test

class AlarmExtensionsTest {

    @Test
    fun `when get the nearest day then should schedule in nearest day`() {
        // When
        val weekDays = listOf(WeekDay.Monday, WeekDay.Tuesday, WeekDay.Friday, WeekDay.Saturday)
        val currentDay = WeekDay.Wednesday

        // When
        val nearestDay = weekDays.getNearestDay(-1, -1, currentDay)

        // Then
        assertEquals(WeekDay.Friday, nearestDay)
    }
}
