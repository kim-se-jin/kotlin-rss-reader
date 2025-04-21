import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class Sites {
    var siteList = listOf("https://www.hankyung.com/feed/it")
    val factory = DocumentBuilderFactory.newInstance()

    fun parsingAll() {
        for (site in siteList) {
            val channel = parsingChannel(site)
            Post(channel).parsingPost() // post를 만들어서 해야할지 함수를 직접호출하는 게 맞을지
        }
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
