package br.com.devlucasyuji.domain

import br.com.devlucasyuji.domain.model.Plant
import br.com.devlucasyuji.domain.repository.PlantRepository
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.plant.AddPlant
import br.com.devlucasyuji.domain.usecase.plant.DeletePlant
import br.com.devlucasyuji.domain.usecase.plant.LoadAllPlant
import br.com.devlucasyuji.domain.usecase.plant.UpdatePlant
import br.com.devlucasyuji.domain.usecase.plant.implementation.AddPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.DeletePlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.LoadAllPlantImpl
import br.com.devlucasyuji.domain.usecase.plant.implementation.UpdatePlantImpl
import br.com.devlucasyuji.test.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import java.io.File
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlantUseCaseTest {

    @get:Rule
    val mainDispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var plantRepository: PlantRepository

    @MockK
    private lateinit var fakePlant: Plant

    private lateinit var addPlantUseCase: AddPlant

    private lateinit var loadPlantUseCase: LoadAllPlant

    private lateinit var updatePlantUseCase: UpdatePlant

    private lateinit var deletePlantUseCase: DeletePlant

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        addPlantUseCase = AddPlantImpl(plantRepository)
        loadPlantUseCase = LoadAllPlantImpl(plantRepository)
        updatePlantUseCase = UpdatePlantImpl(plantRepository)
        deletePlantUseCase = DeletePlantImpl(plantRepository)
    }

    @Test
    fun `when add plant then expect call insert plants on repository`() = runTest {
        every { plantRepository.insertPlants(any()) } returns flowOf(Unit)

        addPlantUseCase(fakePlant).collect {
            verify { plantRepository.insertPlants(listOf(fakePlant)) }
            verify(exactly = 0) { plantRepository.insertPlant(fakePlant) }
        }
    }

    @Test
    fun `when load plant then expect return plants on repository`() = runTest {
        val expectedPlants = listOf(
            Plant(id = 1, "", "", File(""), "", false),
            Plant(id = 2, "", "", File(""), "", false),
            Plant(id = 3, "", "", File(""), "", false)
        )

        every { plantRepository.getPlants() } returns flowOf(expectedPlants)

        loadPlantUseCase().collect { actualPlants ->
            if (actualPlants is Result.Success) {
                Assert.assertEquals(expectedPlants, actualPlants.data)
            }
        }
    }

    @Test
    fun `when update plant then expect call update plants on repository`() = runTest {
        every { plantRepository.updatePlant(fakePlant) } returns flowOf(Unit)

        updatePlantUseCase(fakePlant).collect {
            verify { plantRepository.updatePlant(fakePlant) }
        }
    }

    @Test
    fun `when delete plant then expect call delete plants on repository`() = runTest {
        every { plantRepository.deletePlant(fakePlant) } returns flowOf(Unit)

        deletePlantUseCase(fakePlant).collect {
            verify { plantRepository.deletePlant(fakePlant) }
        }
    }
}