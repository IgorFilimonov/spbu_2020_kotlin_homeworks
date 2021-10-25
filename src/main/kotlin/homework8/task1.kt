package homework8

import homework8.views.StartView
import tornadofx.App
import tornadofx.launch

class MyApp : App(StartView::class)

fun main() {
    launch<MyApp>()
}
