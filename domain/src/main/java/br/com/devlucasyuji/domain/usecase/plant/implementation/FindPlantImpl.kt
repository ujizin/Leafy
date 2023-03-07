package br.com.devlucasyuji.domain.usecase.plant.implementation

import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.result.asResult
import br.com.devlucasyuji.domain.usecase.plant.FindPlant

class FindPlantImpl(
    private val repository: PlantRepository
) : FindPlant {

    override fun invoke(sentence: String) = repository.findPlantBySentence(sentence).asResult()
}