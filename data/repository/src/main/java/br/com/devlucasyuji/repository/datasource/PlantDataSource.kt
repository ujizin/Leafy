package br.com.devlucasyuji.repository.datasource

import br.com.devlucasyuji.repository.model.Plant

/**
 * Interface Plant to data source implementation.
 * */
interface PlantDataSource {

    /**
     * Inserts a new [Plant].
     *
     * @param plant [Plant] to be added
     * */
    suspend fun insertPlant(plant: Plant)

    /**
     * Inserts a new list of [Plant].
     *
     * @param plants list of [Plant] to be added
     * */
    suspend fun insertPlant(plants: List<Plant>)

    /**
     * Get all [Plant] from data source.
     *
     * @return all [Plant] added.
     * */
    suspend fun getPlants(): List<Plant>

    /**
     * Update the [Plant] passed on parameter.
     *
     * @param plant [Plant] to be updated
     * */
    suspend fun updatePlant(plant: Plant)

    /**
     * Delete the plant passed on parameter.
     *
     * @param plant plant to be deleted
     * */
    suspend fun deletePlant(plant: Plant)

    /**
     * Add or update the draft [Plant] passed on parameter.
     *
     * @param plant [Plant] to be added in memory.
     * */
    suspend fun updateDraftPlant(plant: Plant)
}
