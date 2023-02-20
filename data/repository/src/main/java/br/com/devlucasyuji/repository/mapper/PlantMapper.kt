package br.com.devlucasyuji.repository.mapper

import br.com.devlucasyuji.domain.model.Plant
import java.io.File
import br.com.devlucasyuji.repository.model.Plant as DataPlant

/**
 * Plant mapper between domain and data modules.
 * */
internal class PlantMapper {

    fun toDomain(plants: List<DataPlant>) = plants.map { toDomain(it) }

    fun toDomain(plant: DataPlant) = with(plant) {
        Plant(id, title, File(filePath), description, favorite, albumId)
    }

    fun toRepo(plants: List<Plant>) = plants.map { toRepo(it) }

    fun toRepo(plant: Plant) = with(plant) {
        DataPlant(id, title, file.absolutePath, description, favorite, albumId)
    }
}
