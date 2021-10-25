package test2

import tornadofx.App
import tornadofx.launch

@Suppress("MatchingDeclarationName")
class BrowserApp : App(StartView::class)

fun main() {
    launch<BrowserApp>()
}
