@file: Suppress("NoWildcardImports", "WildcardImport")
package test2

import javafx.geometry.Pos
import javafx.scene.control.TextField
import tornadofx.*

class StartView : View() {
    companion object {
        const val prefSize = 500.0
    }
    private val controller: BrowserController by inject()
    var query: TextField by singleAssign()
    override val root = hbox {
        this.alignment = Pos.CENTER
        query = textfield()
        button("Find!") {
            action {
                controller.find()
            }
        }
        setPrefSize(prefSize, prefSize)
    }
}

class UrlFragment : Fragment() {
    val result: String by param()
    override val root = vbox {
        label(result)
    }
}
