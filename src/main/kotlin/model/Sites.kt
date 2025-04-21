package model

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class Sites {
    var siteList =
        // listOf("https://www.hankyung.com/feed/it", "https://www.korea.kr/rss/policy.xml", "https://www.hankyung.com/feed/international")
        listOf("https://v2.velog.io/rss/euisuk-chung", "https://www.hankyung.com/feed/it")
    val factory = DocumentBuilderFactory.newInstance()
    var allPostList = mutableListOf<PostInfo>()

    fun parsingAll(): MutableList<PostInfo> {
        runBlocking {
            for (site in siteList) {
                val channel = async { parsingChannel(site) }
                allPostList.addAll(Post(channel.await()).parsingPost()) // post를 만들어서 해야할지 함수를 직접호출하는 게 맞을지
            }
        }
        return allPostList
    }

    fun parsingChannel(url: String): Node {
        val xml =
            factory
                .newDocumentBuilder()
                .parse(url)
        val channel = xml.getElementsByTagName("channel").item(0)
        return channel
    }
}
