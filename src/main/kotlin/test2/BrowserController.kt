package test2

import tornadofx.Controller

class BrowserController : Controller() {
    fun find() {
        find<StartView>().replaceWith<UrlView>()

    }
}