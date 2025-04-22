package controller

import PostInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Sites
import view.InputView
import view.OutputView
import java.time.Duration

class RssController {
    private val inputView = InputView()
    private val outputView = OutputView()

    val siteLists =
        listOf(
            "https://v2.velog.io/rss/euisuk-chung",
            "https://www.hankyung.com/feed/it",
            "https://www.hankyung.com/feed/realestate",
            "https://www.hankyung.com/feed/finance",
            "https://www.hankyung.com/feed/economy",
        )
    var allPostList: List<PostInfo> = listOf()

    fun run() =
        runBlocking {
            launch(Dispatchers.IO) {
                while (isActive) {
                    val keywordInput = inputView.getKeyword()
                    allPostList = getPostList()
                    var filteredList: List<PostInfo> = listOf()

                    filteredList =
                        allPostList
                            .filter { it.title.contains(keywordInput) }
                            .sortedByDescending { it.pubDate }
                            .take(10)

                    outputView.printResult(filteredList)
                }
            }

            launch {
                while (isActive) {
                    getNewPost()
                }
            }
        }

    suspend fun getPostList(): List<PostInfo> =
        coroutineScope {
            val newPostList =
                siteLists
                    .map { site -> async { Sites(site).parsing() } }
                    .awaitAll()
                    .flatten()
            return@coroutineScope newPostList
        }

    suspend fun getNewPost() {
        // delay(Duration.ofMinutes(10).toMillis())
        delay(Duration.ofSeconds(10).toMillis())

        val newPostList = getPostList().subtract(allPostList)
        if (newPostList.isNotEmpty()) {
            outputView.printNewPostList(newPostList.toList())
            allPostList += newPostList
        }
    }
}
