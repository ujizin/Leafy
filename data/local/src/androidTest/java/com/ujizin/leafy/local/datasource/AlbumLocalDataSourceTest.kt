package com.ujizin.leafy.local.datasource

import com.ujizin.leafy.local.BaseDatabaseTest
import com.ujizin.leafy.local.dao.AlbumDao
import com.ujizin.leafy.local.model.AlbumEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumLocalDataSourceTest : BaseDatabaseTest() {

    private lateinit var albumDao: AlbumDao

    private val fakeAlbum = AlbumEntity(id = 1, title = "fake-title")

    override fun setUp() {
        super.setUp()
        albumDao = db.albumDao()
    }

    @Test
    fun writeAlbumsAndReadInList() = runTest {
        val fakeAlbum2 = AlbumEntity(id = 2, title = "fake-title 2")
        val fakeAlbum3 = AlbumEntity(id = 3, title = "fake-title 3")

        val expectedAlbums = arrayOf(fakeAlbum, fakeAlbum2, fakeAlbum3)
        albumDao.insert(*expectedAlbums)

        val actualAlbums = albumDao.getAll()

        assertEquals(expectedAlbums.toList(), actualAlbums)
    }

    @Test
    fun writeAlbumsAndFindById() = runTest {
        val expected = AlbumEntity(id = 2, title = "another title")
        albumDao.insert(fakeAlbum, expected)

        val actual = albumDao.findAlbumById(2)

        assertEquals(expected, actual)
    }

    @Test
    fun writeAlbumAndUpdate() = runTest {
        val expected = fakeAlbum.copy(title = "updated title")

        albumDao.insert(fakeAlbum)
        albumDao.update(expected)

        val actual = albumDao.findAlbumById(fakeAlbum.id)

        assertEquals(expected, actual)
    }

    @Test
    fun writeAlbumAndDelete() = runTest {
        albumDao.insert(fakeAlbum)

        assertEquals(1, albumDao.getAll().size)

        albumDao.delete(fakeAlbum)

        assertEquals(0, albumDao.getAll().size)
    }
}
