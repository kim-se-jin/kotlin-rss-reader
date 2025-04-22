package view

import PostInfo

class OutputView {
    fun printResult(filteredList: List<PostInfo>) {
        for (i in filteredList.indices) {
            println("[${i + 1}] ${filteredList[i].title} (${filteredList[i].pubDate}) - ${filteredList[i].link}")
        }
    }

    fun printNewPostList(filteredList: List<PostInfo>) {
        println("\n새로운 글이 등록되었습니다!")
        for (i in filteredList.indices) {
            println("[NEW] ${filteredList[i].title} (${filteredList[i].pubDate}) - ${filteredList[i].link}")
        }
        println("\n검색어를 입력하세요 (없으면 전체 출력):")
    }
}
