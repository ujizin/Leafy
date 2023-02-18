package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.dao.PlantDao
import br.com.devlucasyuji.local.mapper.PlantMapper
import br.com.devlucasyuji.repository.datasource.PlantDataSource
import br.com.devlucasyuji.repository.model.Plant

internal class PlantLocalDataSource(
    private val plantDao: PlantDao,
    private val mapper: PlantMapper,
) : PlantDataSource {

    override suspend fun insertPlant(plant: Plant) {
        plantDao.insert(mapper.toPlantEntity(plant))
    }

    override suspend fun insertPlant(plants: List<Plant>) {
        val plantsEntities = plants.map { mapper.toPlantEntity(it) }.toTypedArray()
        plantDao.insert(*plantsEntities)
    }

    override suspend fun getPlants(): List<Plant> = plantDao.getAll().map(mapper::toPlant)

    override suspend fun updatePlant(plant: Plant) {
        plantDao.update(mapper.toPlantEntity(plant))
    }

    override suspend fun deletePlant(plant: Plant) {
        plantDao.delete(mapper.toPlantEntity(plant))
    }
}
