package homework8

import javafx.stage.StageStyle
import tornadofx.*

class MyApp : App(Menu::class)

class Menu : View() {
    override val root = vbox {
        button("menu xd") {
            action {
                //find<Game>().root.lookup()
                close()
            }
        }
        setPrefSize(150.0, 150.0)
    }
}

const val GRID_SIZE = 3

class Game : View() {
    private var currentSymbol = "X"
    private val controller: GameStageController by inject()

    override val root = gridpane {
        for (i in 0 until GRID_SIZE) {
            row {
                for (j in 0 until GRID_SIZE) {
                    button(" ") {
                        gridpaneConstraints {
                            columnRowIndex(i, j)
                            action {
                                if (text == " ") {
                                    text = currentSymbol
                                    when (controller.checkStage(
                                        currentSymbol,
                                        columnIndex!!, rowIndex!!
                                    )) {
                                        GameStages.ONGOING -> {}
                                        GameStages.WIN_OR_LOSE -> {
                                            find<EndGameFragment>(Pair(EndGameFragment::message,
                                                "Player ${currentSymbol} has won!"))
                                                .openModal(stageStyle = StageStyle.TRANSPARENT)
                                        }
                                        GameStages.TIE -> {
                                            find<EndGameFragment>(Pair(EndGameFragment::message, "Tie!"))
                                                .openModal(stageStyle = StageStyle.TRANSPARENT)
                                        }
                                    }
                                    currentSymbol = if (currentSymbol == "X") "O" else "X"
                                }
                            }
                        }
                        setPrefSize(50.0, 50.0)
                    }
                }
            }
        }
    }
}

class EndGameFragment : Fragment() {
    val message: String by param()
    override val root = vbox {
        label(message)
        button("Go to the menu") {
            vboxConstraints {
                marginTop = 10.0
            }
            action {
                find<Game>().replaceWith<Menu>()
                close()
            }
        }
    }
}

enum class GameStages {
    ONGOING,
    WIN_OR_LOSE,
    TIE
}

class GameStageController : Controller() {
    private val grid = Array(GRID_SIZE, { Array(GRID_SIZE, {" "}) })

    fun checkStage(newSymbol: String, newSymbolColumn: Int, newSymbolRow: Int): GameStages {
        grid[newSymbolColumn][newSymbolRow] = newSymbol
        for (i in 0 until GRID_SIZE) {
            var isDifference = false
            for (j in 1 until GRID_SIZE) {
                if (grid[i][j] != grid[i][0]) {
                    isDifference = true
                    break
                }
            }
            if (!isDifference && grid[i][0] != " ")
                return GameStages.WIN_OR_LOSE
        }
        for (i in 0 until GRID_SIZE) {
            var isDifference = false
            for (j in 1 until GRID_SIZE) {
                if (grid[j][i] != grid[0][i]) {
                    isDifference = true
                    break
                }
            }
            if (!isDifference && grid[0][i] != " ")
                return GameStages.WIN_OR_LOSE
        }
        for (i in 1 until GRID_SIZE) {
            if (grid[i][i] == grid[0][0]) {
                if (grid[0][0] != " " && i == GRID_SIZE - 1) {
                    return GameStages.WIN_OR_LOSE
                }
            } else {
                break
            }
        }
        for (i in 1 until GRID_SIZE) {
            if (grid[i][GRID_SIZE - 1 - i] == grid[0][GRID_SIZE - 1]) {
                if (grid[0][GRID_SIZE - 1] != " " && i == GRID_SIZE - 1) {
                    return GameStages.WIN_OR_LOSE
                }
            } else {
                break
            }
        }
        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                if (grid[i][j] == " ") {
                    return GameStages.ONGOING
                }
            }
        }
        return GameStages.TIE
    }
}
