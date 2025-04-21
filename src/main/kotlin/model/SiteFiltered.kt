package model

class SiteFiltered(
    private var keyword: String,
    private var allPostList: MutableList<PostInfo>,
) {
    fun sortByDate() {
        // val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
//        allPostList.sortedByDescending { ZonedDateTime.parse(it.pubDate, formatter) }
//        allPostList = allPostList.sortedByDescending { ZonedDateTime.parse(it.pubDate, formatter) }.take(10).toMutableList()
        allPostList = allPostList.sortedByDescending { it.pubDate }.take(50).toMutableList()
    }

    fun filterByKeyword(): List<PostInfo> {
        val filteredPosts = allPostList.filter { it.title.contains(keyword, true) }
        return filteredPosts
    }
    //
}
