package br.com.devlucasyuji.local.datasource

import br.com.devlucasyuji.local.common.BaseDatabaseTest
import br.com.devlucasyuji.local.dao.AlarmDao
import br.com.devlucasyuji.local.model.AlarmEntity
import br.com.devlucasyuji.local.model.PhotoEntity
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class AlarmLocalDataSourceTest : BaseDatabaseTest() {

    private lateinit var alarmDao: AlarmDao

    private val fakeAlarm = AlarmEntity(id = 1, ring = "alarm", 1)

    private val fakePhoto = PhotoEntity(
        id = 1,
        title = "fake-photo",
        date = "2022-06-20T14:18:11.01",
        filePath = ":data//fake/path",
        favorite = false,
        description = "this is a fake photo",
        albumId = null
    )

    override fun setUp() {
        super.setUp()
        alarmDao = db.alarmDao()

        runTest {
            db.photoDao().insert(fakePhoto)
        }
    }

    @Test
    fun writeAlarmsAndReadInList() = runTest {
        val fakeAlarm2 = AlarmEntity(id = 2, ring = "alarm-2", 1)
        val expectedAlarms = arrayOf(fakeAlarm, fakeAlarm2)
        alarmDao.insert(*expectedAlarms)

        val actualAlarms = alarmDao.getAll()

        assertEquals(expectedAlarms.toList(), actualAlarms)
    }

    @Test
    fun writeAlarmsAndFindByPhotoId() = runTest {
        db.photoDao().insert(fakePhoto.copy(id = 2))

        val fakeAlarm2 = AlarmEntity(id = 2, ring = "alarm-2", 2)
        val fakeAlarm3 = AlarmEntity(id = 2, ring = "alarm-2", 1)

        alarmDao.insert(fakeAlarm, fakeAlarm2, fakeAlarm3)

        val expectedAlarms = listOf(fakeAlarm, fakeAlarm3)
        val actualAlarms = alarmDao.findByPhotoId(1)

        assertEquals(expectedAlarms, actualAlarms)
    }

    @Test
    fun writeAlarmAndUpdate() = runTest {
        val expectedAlarm = fakeAlarm.copy(ring = "updated-alarm")
        alarmDao.insert(fakeAlarm)
        alarmDao.update(expectedAlarm)

        assertEquals(expectedAlarm, alarmDao.getAll().first())
    }

    @Test
    fun writeAlarmAndDelete() = runTest {
        alarmDao.insert(fakeAlarm)

        assertEquals(1, alarmDao.getAll().size)

        alarmDao.delete(fakeAlarm)

        assertEquals(0, alarmDao.getAll().size)
    }
}