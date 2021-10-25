package test2

import tornadofx.App
import tornadofx.launch

class BrowserApp : App(StartView::class)

fun main() {
    launch<BrowserApp>()
}
