package test2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Serializable
data class UrlList(
    @SerialName("results") val results: List<Url>
)

@Serializable
data class Url(
    @SerialName("url") val url: String
)

class SearchEngine {
    companion object {
        const val URL_AMOUNT = 10
    }

    var result = String()
    fun run(text: String): String {
        runBlocking {
            launch { getUrls(text) }
        }
        return result
    }

    fun getUrls(text: String) {
        val query = "https://searx.roughs.ru/search?q=$text&format=json"
        val jsonResult = requestGet(URL(query))
        val json = Json { ignoreUnknownKeys = true }
        val urls = json.decodeFromString<UrlList>(jsonResult)
        for (i in 0 until URL_AMOUNT)
            result += "\n${urls.results[i].url}"
    }

    private fun requestGet(url: URL): String {
        var result = ""
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            inputStream.bufferedReader().use { it.lines().forEach { line -> result += line } }
        }
        return result
    }
}
