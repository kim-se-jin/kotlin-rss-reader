import org.w3c.dom.Node
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.xml.parsers.DocumentBuilderFactory

class Sites {
    var siteList = listOf("https://www.hankyung.com/feed/it")
    val factory = DocumentBuilderFactory.newInstance()
    var allPostList = mutableListOf<PostInfo>()

    fun parsingAll() {
        for (site in siteList) {
            val channel = parsingChannel(site)
            allPostList.addAll(Post(channel).parsingPost()) // post를 만들어서 해야할지 함수를 직접호출하는 게 맞을지
        }
        sortByDate()
    }

    fun parsingChannel(url: String): Node {
        val xml =
            factory
                .newDocumentBuilder()
                .parse(url)
        val channel = xml.getElementsByTagName("channel").item(0)
        return channel
    }

    fun sortByDate() {
        val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
//        allPostList.sortedByDescending { ZonedDateTime.parse(it.pubDate, formatter) }
        allPostList = allPostList.sortedByDescending { ZonedDateTime.parse(it.pubDate, formatter) }.take(10).toMutableList()
//        for (post in allPostList) {
//            println(post.pubDate)
//        }
    }
}
