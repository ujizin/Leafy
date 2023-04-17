package com.ujizin.leafy.features.tasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.domain.model.WeekDay

@Composable
fun TasksRoute(
    viewModel: TasksViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) { viewModel.loadAlarms() }

    Tasks(uiState = uiState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tasks(
    uiState: TasksUiState,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Section(title = stringResource(id = R.string.task_title))
        }

        when (uiState) {
            TasksUiState.Initial -> Unit
            is TasksUiState.Success -> uiState.tasks.forEach { (weekDay, alarms) ->
                stickyHeader { HeaderWeekDay(weekDay) }
                items(alarms) { }
            }
        }
    }
}

@Composable
fun HeaderWeekDay(weekDay: WeekDay) {
    Text(weekDay.name) // TODO add week day header
}

@ThemePreviews
@Composable
private fun TasksPreview() {
}
