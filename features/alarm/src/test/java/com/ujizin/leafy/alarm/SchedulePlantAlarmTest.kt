package com.ujizin.leafy.alarm

import com.ujizin.leafy.alarm.fakes.FakeAlarmScheduler
import com.ujizin.leafy.alarm.fakes.FakeLoadAlarm
import com.ujizin.leafy.alarm.fakes.FakeLoadPlant
import com.ujizin.leafy.alarm.usecase.SchedulePlantAlarm
import com.ujizin.leafy.core.test.TestDispatcherRule
import com.ujizin.leafy.core.ui.extensions.currentDay
import io.mockk.every
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Calendar
import java.util.Calendar.DAY_OF_WEEK

@RunWith(JUnit4::class)
class SchedulePlantAlarmTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = TestDispatcherRule()

    private val alarmScheduler = FakeAlarmScheduler()

    private val loadPlant = FakeLoadPlant(until = COUNTER)

    private val loadAlarm = FakeLoadAlarm(until = COUNTER)

    val schedulePlantAlarm = SchedulePlantAlarm(
        loadPlant = loadPlant,
        loadAlarm = loadAlarm,
        alarmScheduler = alarmScheduler,
    )

    @Test
    fun `test alarm is scheduled will ring in exact time whether enabled`() = runTest {
        var isFlowCollected = false
        mockkStatic(Calendar::class)

        loadAlarm.alarms.forEach { alarm ->
            (1..7).forEach { dayOfTheWeek ->
                every { Calendar.getInstance().get(DAY_OF_WEEK) } returns dayOfTheWeek

                schedulePlantAlarm(alarm.id)
                    .onStart { isFlowCollected = false }
                    .onCompletion {
                        assertEquals(alarm.weekDays.contains(currentDay), isFlowCollected)
                    }
                    .collect {
                        isFlowCollected = true

                        val alarmScheduled = alarmScheduler.alarms.find { scheduled ->
                            scheduled.hours == alarm.hours && scheduled.minutes == alarm.minutes
                        }

                        assertNotNull(alarmScheduled)
                        assertEquals(alarm.plantId, it.id)
                    }
            }
        }
    }

    companion object {
        private const val COUNTER = 100
    }
}
