package com.ujizin.leafy.publish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.domain.usecase.plant.AddDraftPlant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    private val addDraftPlant: AddDraftPlant
) : ViewModel() {

    fun sendDraftPlant(title: String, description: String, onFinishPublish: () -> Unit) {
        addDraftPlant(
            title = title,
            description = description
        ).onCompletion {
            onFinishPublish()
        }.launchIn(viewModelScope)
    }
}
