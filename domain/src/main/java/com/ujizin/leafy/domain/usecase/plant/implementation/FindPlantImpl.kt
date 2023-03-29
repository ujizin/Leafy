package com.ujizin.leafy.domain.usecase.plant.implementation

import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.domain.result.asResult
import com.ujizin.leafy.domain.usecase.plant.FindPlant

class FindPlantImpl(
    private val repository: PlantRepository
) : FindPlant {

    override fun invoke(sentence: String) = repository.findPlantBySentence(sentence).asResult()
}