package br.com.devlucasyuji.home

import androidx.lifecycle.ViewModel
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadAllPhoto: LoadAllPhoto,
): ViewModel() {

}
