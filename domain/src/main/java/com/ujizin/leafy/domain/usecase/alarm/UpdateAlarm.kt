package com.ujizin.leafy.domain.usecase.alarm

import com.ujizin.leafy.domain.model.Alarm
import kotlinx.coroutines.flow.Flow

/**
 * Update alarm use case.
 * */
interface UpdateAlarm {

    /**
     * Update alarm on data source.
     *
     * @param alarm alarm to be updated
     * */
    operator fun invoke(alarm: Alarm): Flow<Unit>
}