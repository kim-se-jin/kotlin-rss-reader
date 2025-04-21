package view

import model.PostInfo

class OutputView {
    fun printResult(filteredList: List<PostInfo>) {
        for (i in filteredList.indices) {
            println("[${i + 1}] ${filteredList[i].title} (${filteredList[i].pubDate}) - ${filteredList[i].link}")
        }
    }
}
