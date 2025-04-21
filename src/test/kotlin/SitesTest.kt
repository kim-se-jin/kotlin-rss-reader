import io.kotest.matchers.shouldBe
import model.PostInfo
import model.Sites
import org.junit.jupiter.api.Test

class SitesTest {
    @Test
    fun sortByDate() {
        var site = Sites()
        site.parsingAll()
        val size = site.allPostList.size

        size shouldBe 10
    }

    @Test
    fun parsingAll() {
        var site = Sites()
        site.parsingAll()
    }

    @Test
    fun parsingChannel() {
    }

    @Test
    fun filterByKeyword() {
        var postLists: MutableList<PostInfo> = mutableListOf()
        postLists.add(PostInfo("국내", "www.naver.com", "Fri, 18 Apr 2025 15:03:42 +0900", "홍길동"))
        postLists.add(PostInfo("해외", "www.google.com", "Fri, 22 Apr 2025 15:03:42 +0900", "구그링"))
        postLists.add(PostInfo("국내", "www.daum.com", "Fri, 17 Apr 2025 15:03:42 +0900", "다우미"))

        val site = Sites()
        val filteredPosts = site.filterByKeyword(postLists, "국내")
        filteredPosts.size shouldBe 2
    }
}
