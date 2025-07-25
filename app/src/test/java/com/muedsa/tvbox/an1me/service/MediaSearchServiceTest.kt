package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.TestPlugin
import com.muedsa.tvbox.an1me.checkMediaCardRow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class MediaSearchServiceTest {

    private val service = TestPlugin.provideMediaSearchService()

    @Test
    fun searchMedias_test() = runTest {
        val row = service.searchMedias("GIRLS BAND CRY")
        checkMediaCardRow(row = row)
    }
}