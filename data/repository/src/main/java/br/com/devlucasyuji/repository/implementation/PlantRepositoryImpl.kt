package br.com.devlucasyuji.repository.implementation

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.repository.datasource.PlantDataSource
import br.com.devlucasyuji.repository.mapper.PlantMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PlantRepositoryImpl(
    private val dataSource: PlantDataSource,
    private val mapper: PlantMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlantRepository {

    override fun getPlants(): Flow<List<Plant>> = flow {
        emit(mapper.toDomain(dataSource.getPlants()))
    }.flowOn(dispatcher)

    override fun insertPlant(plant: Plant) = flow {
        emit(dataSource.insertPlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)

    override fun insertPlants(plants: List<Plant>) = flow {
        emit(dataSource.insertPlant(mapper.toRepo(plants)))
    }.flowOn(dispatcher)

    override fun updatePlant(plant: Plant) = flow {
        emit(dataSource.updatePlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)

    override fun deletePlant(plant: Plant) = flow {
        emit(dataSource.deletePlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)

    override fun updateDraftPlant(plant: Plant) = flow {
        emit(dataSource.updateDraftPlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)
}