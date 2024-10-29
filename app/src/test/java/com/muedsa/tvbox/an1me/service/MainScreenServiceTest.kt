package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.TestPlugin
import com.muedsa.tvbox.an1me.checkMediaCardRows
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainScreenServiceTest {

    private val service = TestPlugin.provideMainScreenService()

    @Test
    fun getRowsDataTest() = runTest{
        val rows = service.getRowsData()
        checkMediaCardRows(rows = rows)
    }

}