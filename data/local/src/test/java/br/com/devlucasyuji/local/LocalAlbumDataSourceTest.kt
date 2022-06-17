package br.com.devlucasyuji.local

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import br.com.devlucasyuji.local.dao.AlbumDao
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LocalAlbumDataSourceTest : BaseDatabaseTest() {

    private lateinit var albumDao: AlbumDao

    override fun setUp() {
        super.setUp()
        albumDao = db.albumDao()
    }
}