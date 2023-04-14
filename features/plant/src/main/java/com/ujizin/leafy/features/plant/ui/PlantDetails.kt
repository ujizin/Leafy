package com.ujizin.leafy.features.plant.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.features.plant.DetailPlantUiState
import com.ujizin.leafy.features.plant.DetailPlantViewModel
import java.io.File

@Composable
fun PlantDetailsRoute(
    onBackPressed: OnClick,
    viewModel: DetailPlantViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PlantDetails(
        onBackPressed = onBackPressed,
        uiState = uiState,
        onAlarmChanged = viewModel::updateAlarm,
        onDeleteClick = { plant ->
            viewModel.deletePlant(plant, onBackPressed)
        }
    )
}

@Composable
private fun PlantDetails(
    onBackPressed: OnClick,
    uiState: DetailPlantUiState,
    onAlarmChanged: (Alarm) -> Unit,
    onDeleteClick: (Plant) -> Unit,
) {
    when (val result: DetailPlantUiState = uiState) {
        DetailPlantUiState.Initial -> PlantDetailsLoading()
        DetailPlantUiState.NotFound -> PlantDetailsNotFound()
        is DetailPlantUiState.Loaded -> PlantDetailsContent(
            onBackPressed = onBackPressed,
            plant = result.plant,
            alarms = result.alarms,
            onAlarmChanged = onAlarmChanged,
            onDeleteClick = onDeleteClick,
        )
    }
}

@ThemePreviews
@Composable
private fun PlantDetailsPreview() {
    LeafyTheme {
        Surface {
            PlantDetails(
                onBackPressed = {},
                uiState = DetailPlantUiState.Loaded(
                    Plant(
                        1,
                        "Plant foo",
                        "Description",
                        File("foo"),
                        false,
                    ),
                    alarms = listOf(),
                ),
                onAlarmChanged = {},
                onDeleteClick = {},
            )
        }
    }
}
