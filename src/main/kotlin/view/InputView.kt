package view

import java.util.Scanner

class InputView {
    private val reader = Scanner(System.`in`)

    fun getKeyword(): String {
        println("검색어를 입력하세요 (없으면 전체 출력):")
        val input = reader.nextLine()
        return input
    }
}
