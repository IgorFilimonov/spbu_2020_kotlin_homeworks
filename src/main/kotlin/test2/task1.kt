package test2

import tornadofx.App
import tornadofx.launch

class Browser : App(StartView::class)

fun main() {
    launch<Browser>()
}
