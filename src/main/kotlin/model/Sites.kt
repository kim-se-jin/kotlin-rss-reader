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

    fun parsing(): MutableList<PostInfo> {
        val xml = factory.newDocumentBuilder().parse(link)
        val channel = xml.getElementsByTagName("channel").item(0)

        val items =
            List(channel.childNodes.length) { channel.childNodes.item(it) }
                .filterIsInstance<Element>()
                .filter { it.tagName == "item" }

        var filteredList: MutableList<PostInfo> = mutableListOf()

        items.forEach {
            filteredList.add(
                PostInfo(
                    it.textOf("title"),
                    it.textOf("link"),
                    LocalDateTime.parse(it.textOf("pubDate"), DateTimeFormatter.RFC_1123_DATE_TIME).toString(),
                ),
            )
        }

        return filteredList
    }

    private fun Element.textOf(tagName: String): String = getElementsByTagName(tagName).item(0)?.textContent.orEmpty()
}
