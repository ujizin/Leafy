package com.ujizin.leafy.preferences.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.play.core.review.ReviewManagerFactory
import com.ujizin.leafy.core.ui.components.selector.ButtonRow
import com.ujizin.leafy.core.ui.extensions.openAppInPlayStore
import com.ujizin.leafy.core.ui.extensions.versionName
import com.ujizin.leafy.features.preferences.R
import com.ujizin.leafy.preferences.utils.rememberReviewTask

@Composable
internal fun RateAppSelector(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var redirectToPlayStore by remember { mutableStateOf(false) }
    val reviewManager = remember {
        ReviewManagerFactory.create(context)
    }

    val reviewInfo = rememberReviewTask(
        reviewManager = reviewManager,
        onError = { redirectToPlayStore = true },
    )

    ButtonRow(
        modifier = modifier,
        title = stringResource(R.string.rate_app),
        subTitle = context.versionName?.let { stringResource(R.string.version, it) },
    ) {
        when {
            redirectToPlayStore -> context.openAppInPlayStore()
            else -> reviewInfo?.let {
                reviewManager.launchReviewFlow(context as Activity, reviewInfo)
                    .addOnCompleteListener {
                        redirectToPlayStore = true
                    }
            }
        }
    }
}
