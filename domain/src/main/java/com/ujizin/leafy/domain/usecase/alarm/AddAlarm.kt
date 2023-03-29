package com.ujizin.leafy.domain.usecase.alarm

import com.ujizin.leafy.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

/**
 * Add alarm use case.
 * */
interface AddAlarm {

    /**
     * Add alarm on data source.
     *
     * @param alarm alarm to be added
     * */
    operator fun invoke(alarm: Alarm): Flow<Unit>
}
