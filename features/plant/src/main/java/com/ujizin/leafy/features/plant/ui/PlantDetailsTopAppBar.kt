package com.ujizin.leafy.features.plant.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.dropdown.DropdownWarningMenuItem
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.features.plant.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun PlantDetailsTopAppBar(
    onBackPressed: OnClick,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onSharedClick: OnClick,
    onEditClick: OnClick,
    onDeleteClick: OnClick,
) {
    TopAppBar(
        windowInsets = WindowInsets(0.dp),
        navigationIcon = {
            AnimatedButtonIcon(
                icon = Icons.Back,
                size = 24.dp,
                onClick = onBackPressed,
            )
        },
        title = {
            Text(
                text = title.capitalize(),
                style = MaterialTheme.typography.titleSmall,
            )
        },
        actions = {
            var isExpanded by remember { mutableStateOf(false) }

            AnimatedButtonIcon(
                icon = Icons.Shared,
                size = 24.dp,
                onClick = onSharedClick,
            )
            AnimatedDropDownMenu(
                isExpanded = isExpanded,
                icon = Icons.Dropdown,
                onExpandedChanged = { isExpanded = it },
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.edit).capitalize()) },
                    onClick = onEditClick,
                )
                DropdownWarningMenuItem(
                    text = { Text(stringResource(R.string.delete).capitalize()) },
                    warningTitle = stringResource(R.string.warning_delete_title),
                    warningText = stringResource(R.string.warning_delete_text),
                    warningConfirmText = stringResource(R.string.warning_delete_confirm),
                    warningDismissText = stringResource(R.string.warning_delete_dismiss),
                    onConfirmClick = onDeleteClick,
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun AnimatedDropDownMenu(
    modifier: Modifier = Modifier,
    icon: Icons,
    isExpanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    AnimatedButtonIcon(
        modifier = modifier,
        icon = icon,
        size = 24.dp,
        onClick = { onExpandedChanged(true) }
    )
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(0.5F),
        expanded = isExpanded,
        onDismissRequest = { onExpandedChanged(false) },
        content = content
    )
}