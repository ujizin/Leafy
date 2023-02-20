package br.com.devlucasyuji.local.mapper

import br.com.devlucasyuji.local.model.PlantEntity
import br.com.devlucasyuji.repository.model.Plant

/**
 * Mapper between [Plant] and [PlantEntity]
 * */
internal class PlantMapper {

    fun toPlant(plantEntity: PlantEntity) = with(plantEntity) {
        Plant(id, title, filePath, description, favorite, albumId)
    }

    fun toPlantEntity(plant: Plant) = with(plant) {
        PlantEntity(id, title, filePath, description, favorite, albumId)
    }
}
