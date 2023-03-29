package com.ujizin.leafy.local.mapper

import com.ujizin.leafy.local.model.PlantEntity
import com.ujizin.leafy.repository.model.Plant

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
