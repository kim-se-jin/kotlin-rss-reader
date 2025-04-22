package model

import PostInfo
import org.w3c.dom.Element
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory

class Sites(
    val link: String,
) {
    val factory = DocumentBuilderFactory.newInstance()

    fun parsing(): List<PostInfo> {
        val xml = factory.newDocumentBuilder().parse(link)
        val channel = xml.getElementsByTagName("channel").item(0)

        val items =
            (0 until channel.childNodes.length)
                .asSequence()
                .map { channel.childNodes.item(it) }
                .filterIsInstance<Element>()
                .filter { it.tagName == "item" }

        return items
            .map { element ->
                PostInfo(
                    title = element.textOf("title"),
                    link = element.textOf("link"),
                    pubDate =
                        LocalDateTime
                            .parse(
                                element.textOf("pubDate"),
                                DateTimeFormatter.RFC_1123_DATE_TIME,
                            ).toString(),
                )
            }.toList()
    }

    private fun Element.textOf(tagName: String): String = getElementsByTagName(tagName).item(0)?.textContent.orEmpty()
}
