import io.kotest.matchers.shouldBe
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
}
