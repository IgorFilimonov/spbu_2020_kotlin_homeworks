@file: Suppress("NoWildcardImports", "WildcardImport")
package homework8.views

import homework8.GameController
import javafx.geometry.Pos
import javafx.scene.control.ContentDisplay
import tornadofx.*

class GameView : View() {
    companion object {
        const val GRID_SIZE = 3
        const val PREF_SIZE = 50.0
    }

    private val controller: GameController by inject()
    override val root = gridpane {
        for (i in 0 until GRID_SIZE) {
            row {
                for (j in 0 until GRID_SIZE) {
                    button(" ") {
                        gridpaneConstraints {
                            id = "${i * GRID_SIZE + j}"
                            action {
                                controller.makeMove(i, j)
                            }
                        }
                        setPrefSize(PREF_SIZE, PREF_SIZE)
                        contentDisplay = ContentDisplay.CENTER
                    }
                }
            }
        }
    }
}

class EndGameFragment : Fragment() {
    companion object {
        const val MARGIN_TOP = 10.0
    }
    val message: String by param()
    override val root = vbox {
        this.alignment = Pos.CENTER
        label(message)
        button("Go to the menu") {
            vboxConstraints {
                marginTop = MARGIN_TOP
            }
            action {
                find<GameView>().replaceWith<StartView>()
                close()
            }
        }
    }
}
