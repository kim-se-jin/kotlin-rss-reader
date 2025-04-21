package model

import kotlinx.datetime.format.DateTimeComponents
import org.w3c.dom.Element
import org.w3c.dom.Node

class Post(
    val channel: Node,
) {
    var postList: MutableList<PostInfo> = mutableListOf()

    fun parsingPost(): MutableList<PostInfo> {
        val items = (channel as Element).getElementsByTagName("item")
        for (i in 0 until items.length) {
            val item = items.item(i) as Element
            val itemTitle = item.getElementsByTagName("title").item(0).textContent
            val itemLink = item.getElementsByTagName("link").item(0).textContent
            val itemDate = item.getElementsByTagName("pubDate").item(0).textContent
            val parseItemDate = parsingDate(itemDate)
            // val itemAuthor = item.getElementsByTagName("author").item(0).textContent
            val itemAuthor = "홍길동"
            postList.add(PostInfo(itemTitle, itemLink, parseItemDate, itemAuthor))
        }
        return postList
    }

    fun parsingDate(itemDate: String): String {
        val components = DateTimeComponents.Formats.RFC_1123.parse(itemDate)
        return components.toLocalDate().toString()
    }
}

class PostInfo(
    var title: String = "",
    var link: String = "",
    var pubDate: String = "",
    var author: String = "",
)
