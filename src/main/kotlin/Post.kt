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
            val itemAuthor = item.getElementsByTagName("author").item(0).textContent
            postList.add(PostInfo(itemTitle, itemLink, itemDate, itemAuthor))
        }
        return postList
    }
}

class PostInfo(
    var title: String = "",
    var link: String = "",
    var pubDate: String = "",
    var author: String = "",
)
