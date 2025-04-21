import io.kotest.matchers.shouldBe
import model.Post
import model.Sites
import org.junit.jupiter.api.Test

class PostTest {
    @Test
    fun parsingPost() {
        val channel = Sites().parsingChannel("https://www.hankyung.com/feed/it")
        val post = Post(channel)
        post.parsingPost()

        post.postList[0].title shouldBe "종근당고촌학원, '민족자립 교육 100년' 대동세무고 기념식"
        post.postList[0].link shouldBe "https://www.hankyung.com/article/202504214833i"
        post.postList[0].pubDate shouldBe "Mon, 21 Apr 2025 14:44:36 +0900"
        post.postList[0].author shouldBe "이지현"
    }
}
