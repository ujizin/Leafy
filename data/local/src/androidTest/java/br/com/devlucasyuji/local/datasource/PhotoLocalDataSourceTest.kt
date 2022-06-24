package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.common.BaseDatabaseTest
import br.com.devlucasyuji.local.dao.PhotoDao
import br.com.devlucasyuji.local.model.AlbumEntity
import br.com.devlucasyuji.local.model.PhotoEntity
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotoLocalDataSourceTest : BaseDatabaseTest() {

    private lateinit var photoDao: PhotoDao

    private val fakePhoto = PhotoEntity(
        id = 1,
        title = "fake-photo",
        date = "2022-06-20T14:16:11.01",
        filePath = ":data//fake/path",
        favorite = false,
        description = "this is a fake photo",
        albumId = null
    )

    override fun setUp() {
        super.setUp()
        photoDao = db.photoDao()
    }

    @Test
    fun writePhotoAndReadInList() = runTest {
        val fakePhoto2 = fakePhoto.copy(id = 2, title = "fake-photo 2")
        val fakePhoto3 = fakePhoto.copy(id = 3, title = "fake-photo 3")

        val expectedPhotos = arrayOf(fakePhoto, fakePhoto2, fakePhoto3)
        photoDao.insert(*expectedPhotos)

        val actualPhotos = photoDao.getAll()
        assertEquals(expectedPhotos.toList(), actualPhotos)
    }

    @Test
    fun writePhotoAndFindByAlbumId() = runTest {
        val fakeAlbum = AlbumEntity(1, "title album")
        val fakeAlbum2 = AlbumEntity(2, "title album")

        db.albumDao().insert(fakeAlbum, fakeAlbum2)

        val fakePhoto2 = fakePhoto.copy(id = 2, title = "fake-photo 2", albumId = 1)
        val fakePhoto3 = fakePhoto.copy(id = 3, title = "fake-photo 3", albumId = 2)
        val fakePhoto4 = fakePhoto.copy(id = 4, title = "fake-photo 4", albumId = 2)

        photoDao.insert(fakePhoto, fakePhoto2, fakePhoto3, fakePhoto4)

        val expectedPhotos = arrayOf(fakePhoto3, fakePhoto4)

        val actualPhotos = photoDao.findByAlbumId(2)

        assertEquals(expectedPhotos.toList(), actualPhotos)
    }

    @Test
    fun writePhotoAndUpdate() = runTest {
        val expectedPhoto = fakePhoto.copy(title = "updated title")

        photoDao.insert(fakePhoto)

        photoDao.update(expectedPhoto)

        assertEquals(expectedPhoto, photoDao.getAll().first())
    }

    @Test
    fun writePhotoAndDelete() = runTest {
        photoDao.insert(fakePhoto)

        assertEquals(1, photoDao.getAll().size)

        photoDao.delete(fakePhoto)

        assertEquals(0, photoDao.getAll().size)
    }
}