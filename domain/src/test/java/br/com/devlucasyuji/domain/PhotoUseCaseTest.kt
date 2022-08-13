package br.com.devlucasyuji.domain

import br.com.devlucasyuji.domain.model.Photo
import br.com.devlucasyuji.domain.repository.PhotoRepository
import br.com.devlucasyuji.domain.result.Result
import br.com.devlucasyuji.domain.usecase.photo.AddPhoto
import br.com.devlucasyuji.domain.usecase.photo.DeletePhoto
import br.com.devlucasyuji.domain.usecase.photo.LoadAllPhoto
import br.com.devlucasyuji.domain.usecase.photo.UpdatePhoto
import br.com.devlucasyuji.domain.usecase.photo.implementation.AddPhotoImpl
import br.com.devlucasyuji.domain.usecase.photo.implementation.DeletePhotoImpl
import br.com.devlucasyuji.domain.usecase.photo.implementation.LoadAllPhotoImpl
import br.com.devlucasyuji.domain.usecase.photo.implementation.UpdatePhotoImpl
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
class PhotoUseCaseTest {

    @get:Rule
    val mainDispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var photoRepository: PhotoRepository

    @MockK
    private lateinit var fakePhoto: Photo

    private lateinit var addPhotoUseCase: AddPhoto

    private lateinit var loadPhotoUseCase: LoadAllPhoto

    private lateinit var updatePhotoUseCase: UpdatePhoto

    private lateinit var deletePhotoUseCase: DeletePhoto

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        addPhotoUseCase = AddPhotoImpl(photoRepository)
        loadPhotoUseCase = LoadAllPhotoImpl(photoRepository)
        updatePhotoUseCase = UpdatePhotoImpl(photoRepository)
        deletePhotoUseCase = DeletePhotoImpl(photoRepository)
    }

    @Test
    fun `when add photo then expect call insert photos on repository`() = runTest {
        every { photoRepository.insertPhotos(any()) } returns flowOf(Unit)

        addPhotoUseCase(fakePhoto).collect {
            verify { photoRepository.insertPhotos(listOf(fakePhoto)) }
            verify(exactly = 0) { photoRepository.insertPhoto(fakePhoto) }
        }
    }

    @Test
    fun `when load photo then expect return photos on repository`() = runTest {
        val expectedPhotos = listOf(
            Photo(id = 1, "", "", File(""), "", false),
            Photo(id = 2, "", "", File(""), "", false),
            Photo(id = 3, "", "", File(""), "", false)
        )

        every { photoRepository.getPhotos() } returns flowOf(expectedPhotos)

        loadPhotoUseCase().collect { actualPhotos ->
            if (actualPhotos is Result.Success) {
                Assert.assertEquals(expectedPhotos, actualPhotos.data)
            }
        }
    }

    @Test
    fun `when update photo then expect call update photos on repository`() = runTest {
        every { photoRepository.updatePhoto(fakePhoto) } returns flowOf(Unit)

        updatePhotoUseCase(fakePhoto).collect {
            verify { photoRepository.updatePhoto(fakePhoto) }
        }
    }

    @Test
    fun `when delete photo then expect call delete photos on repository`() = runTest {
        every { photoRepository.deletePhoto(fakePhoto) } returns flowOf(Unit)

        deletePhotoUseCase(fakePhoto).collect {
            verify { photoRepository.deletePhoto(fakePhoto) }
        }
    }
}