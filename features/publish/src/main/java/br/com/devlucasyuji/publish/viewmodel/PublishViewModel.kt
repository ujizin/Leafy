package br.com.devlucasyuji.publish.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.devlucasyuji.navigation.Args
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val image = Uri.decode(savedStateHandle.get<String>(Args.ImageFilePath).orEmpty())

}
