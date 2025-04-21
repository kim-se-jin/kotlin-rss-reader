package controller

import model.SiteFiltered
import model.Sites
import view.InputView
import view.OutputView

class RssController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val keywordInput = inputView.getKeyword()
        val allPostList = Sites().parsingAll()
        val siteFiltered = SiteFiltered(keywordInput, allPostList)
        siteFiltered.sortByDate()
        val filteredList = siteFiltered.filterByKeyword()
        println("test")

        outputView.printResult(filteredList)
    }
}
