package test2

import tornadofx.App
import tornadofx.launch

class Browser : App(StartView::class)

fun main() {
    /*val searchEngine = SearchEngine()
    searchEngine.run("test")*/
    launch<Browser>()
}