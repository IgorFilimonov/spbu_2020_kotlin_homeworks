@file: Suppress("NoWildcardImports", "WildcardImport")
package homework8.views

import homework8.GameController
import javafx.geometry.Pos
import javafx.scene.control.ContentDisplay
import tornadofx.*

class GameView : View() {
    companion object {
        const val GRID_SIZE = 3
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
                        setPrefSize(50.0, 50.0)
                        contentDisplay = ContentDisplay.CENTER
                    }
                }
            }
        }
    }
}

class EndGameFragment : Fragment() {
    val message: String by param()
    override val root = vbox {
        this.alignment = Pos.CENTER
        label(message)
        button("Go to the menu") {
            vboxConstraints {
                marginTop = 10.0
            }
            action {
                find<GameView>().replaceWith<StartView>()
                close()
            }
        }
    }
}
