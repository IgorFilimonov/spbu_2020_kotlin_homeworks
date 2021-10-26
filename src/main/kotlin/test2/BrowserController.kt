package test2

import tornadofx.Controller

class BrowserController : Controller() {
    fun find() {
        val searchEngine = SearchEngine()
        val result = searchEngine.run(find<StartView>().query.text)
        find<UrlFragment>(Pair(UrlFragment::result, result)).openModal()
    }
}
