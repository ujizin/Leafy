package com.ujizin.leafy.repository.implementation

import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.repository.PlantRepository
import com.ujizin.leafy.repository.datasource.PlantDataSource
import com.ujizin.leafy.repository.mapper.PlantMapper
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

    override fun getPlant(id: Long): Flow<Plant?> = flow {
        emit(dataSource.getPlant(id)?.let(mapper::toDomain))
    }.flowOn(dispatcher)

    override fun insertPlant(plant: Plant) = flow {
        emit(dataSource.insertPlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)

    override fun insertPlants(plants: List<Plant>) = flow {
        emit(dataSource.insertPlants(mapper.toRepo(plants)))
    }.flowOn(dispatcher)

    override fun updatePlant(plant: Plant) = flow {
        emit(dataSource.updatePlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)

    override fun deletePlant(plant: Plant) = flow {
        emit(dataSource.deletePlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)

    override fun findPlantBySentence(sentence: String) = flow {
        emit(dataSource.findPlantBySentence(sentence).map(mapper::toDomain))
    }.flowOn(dispatcher)

    override fun getDraftPlant() = flow {
        emit(
            dataSource.getDraftPlant()?.let(mapper::toDomain)
                ?: Plant.createDraft(),
        )
    }.flowOn(dispatcher)

    override fun updateDraftPlant(plant: Plant) = flow {
        emit(dataSource.updateDraftPlant(mapper.toRepo(plant)))
    }.flowOn(dispatcher)
}
