package com.ujizin.leafy.alarm.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ujizin.leafy.alarm.ui.components.modal.ModalValue
import com.ujizin.leafy.core.ui.extensions.displayNameRes
import com.ujizin.leafy.domain.model.WeekDay

@Composable
fun List<WeekDay>.mapToModalValue(): List<ModalValue<WeekDay>> = map {
    ModalValue(stringResource(it.displayNameRes), it)
}
