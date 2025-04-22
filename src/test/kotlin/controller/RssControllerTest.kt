package controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class RssControllerTest {
    private val testDispatcher = StandardTestDispatcher()
    private val rssController = RssController()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPostList_() =
        runTest {
            val result = rssController.getPostList()
            advanceUntilIdle()
            assertTrue(result.isNotEmpty())
//            var allPostList = listOf(PostInfo("title1","link","2025-04-22"),
//                PostInfo("title2","link","2025-04-21"))
//
//            var newPostList:MutableList<PostInfo> = mutableListOf()
//            val job =
//                launch(Dispatchers.IO) {
//                    delay(1000)
//                    println("작업 완료")
//                }
//            job.join()
//            assertTrue(job.isCompleted)

//            if (allPostList.isNotEmpty()) { // test code
//                val name = "title" + i++
//                newPostList.add(PostInfo(name, "link", "2025-04-22"))
//            }
        }
}
