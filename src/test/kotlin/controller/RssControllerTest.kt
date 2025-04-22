@file:Suppress("ktlint:standard:no-wildcard-imports")

import controller.RssController
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import model.Sites
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import java.time.LocalDateTime
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RssControllerTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var rssController: RssController

    @BeforeAll
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterAll
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @BeforeEach
    fun init() {
        rssController = RssController()
    }

    @Test
    fun `getPostList should return combined list from all sites`() =
        runTest {
            // Arrange
            val mockPost = PostInfo("Test Title", "http://test.com", LocalDateTime.now().toString())
            mockkConstructor(Sites::class)
            every { anyConstructed<Sites>().parsing() } returns listOf(mockPost)

            // Act
            val result = rssController.getPostList()

            // Assert
            Assertions.assertEquals(rssController.siteLists.size, result.size)
            result.forEach {
                Assertions.assertEquals("Test Title", it.title)
            }

            unmockkAll()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getNewPost should update allPostList with new posts`() =
        runTest {
            // Arrange
            val initialPost = PostInfo("Initial Title", "http://initial.com", LocalDateTime.now().toString())
            val newPost = PostInfo("New Title", "http://new.com", LocalDateTime.now().plusMinutes(1).toString())

            mockkConstructor(Sites::class)
            every { anyConstructed<Sites>().parsing() } returnsMany
                listOf(
                    listOf(initialPost),
                    listOf(initialPost, newPost),
                )

            rssController.allPostList = listOf(initialPost)

            // Act
            rssController.getNewPost()
            advanceUntilIdle()

            // Assert
            Assertions.assertTrue(rssController.allPostList.contains(newPost))

            unmockkAll()
        }
}
