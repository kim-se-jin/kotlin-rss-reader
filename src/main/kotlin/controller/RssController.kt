package controller

import PostInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Sites
import view.InputView
import view.OutputView
import java.time.Duration

var i = 0

class RssController {
    private val inputView = InputView()
    private val outputView = OutputView()

    val siteLists = listOf("https://v2.velog.io/rss/euisuk-chung", "https://www.hankyung.com/feed/it")
    var allPostList: MutableList<PostInfo> = mutableListOf()

    fun run() =
        runBlocking {
            launch(Dispatchers.IO) {
                while (isActive) {
                    val keywordInput = inputView.getKeyword()
                    allPostList = getPostList()
                    var filteredList: MutableList<PostInfo> = mutableListOf()

                    filteredList =
                        allPostList
                            .filter { it.title.contains(keywordInput) }
                            .sortedByDescending { it.pubDate }
                            .take(10)
                            .toMutableList()

                    outputView.printResult(filteredList)
                }
            }

            launch {
                while (isActive) {
//                    delay(Duration.ofMinutes(10).toMillis())
                    delay(Duration.ofSeconds(10).toMillis())
                    // 비교하고 있으면 더하는 로직
                    val newPostList = getPostList().subtract(allPostList)
                    if (newPostList.isNotEmpty()) {
                        outputView.printNewPostList(newPostList.toList())
                        allPostList.addAll(newPostList)
                    }
                }
            }
        }

    suspend fun getPostList(): MutableList<PostInfo> =
        coroutineScope {
            val newPostList: MutableList<PostInfo> = mutableListOf()

            for (site in siteLists) {
                val postLists = async { Sites(site).parsing() }
                newPostList += postLists.await()
            }

            if (allPostList.isNotEmpty()) { // test code
                val name = "title" + i++
                newPostList.add(PostInfo(name, "link", "2025-04-22"))
            }

            return@coroutineScope newPostList
        }
}
