package com.ujizin.leafy.preferences.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager

@Composable
fun rememberReviewTask(reviewManager: ReviewManager, onError: () -> Unit): ReviewInfo? {
    var reviewInfo: ReviewInfo? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(Unit) {
        reviewManager.requestReviewFlow().addOnCompleteListener {
            if (it.isSuccessful) reviewInfo = it.result else onError()
        }
    }

    return reviewInfo
}
