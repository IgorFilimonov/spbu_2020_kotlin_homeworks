package homework8

import homework8.model.GameModel
import homework8.views.EndGameFragment
import homework8.views.GameView
import homework8.views.StartView
import javafx.scene.control.Button
import javafx.stage.StageStyle
import tornadofx.Controller

enum class GameStages {
    ONGOING,
    WIN_OR_LOSE,
    TIE
}

class GameController : Controller() {
    companion object {
        const val GRID_SIZE = 3
    }

    private var model = GameModel(this)
    var isBotEnabled = false
    var playerSide = 'X'
    var botSide = 'O'
    var isBotEasy = true

    fun startGame() {
        refreshButtons()
        model = GameModel(this)
        model.triggerBot()
        find<StartView>().replaceWith<GameView>()
    }

    private fun refreshButtons() {
        for (row in 0 until GRID_SIZE) {
            for (column in 0 until GRID_SIZE) {
                val button = find<GameView>().root.lookup("#${row * GRID_SIZE + column}") as Button
                button.text = " "
            }
        }
    }

    fun makeMove(row: Int, column: Int) {
        val button = find<GameView>().root.lookup("#${row * GRID_SIZE + column}") as Button
        if (button.text == " ") {
            if (model.movesAmount % 2 == 0)
                button.text = "X"
            else
                button.text = "O"
            model.makeMove(row, column)
        }
    }

    fun finishGame(result: GameStages) {
        val lastSymbol = if (model.movesAmount % 2 == 0)
            'O'
        else
            'X'
        if (result == GameStages.WIN_OR_LOSE)
            find<EndGameFragment>(Pair(EndGameFragment::message,
                "Player $lastSymbol has won!"))
                .openModal(stageStyle = StageStyle.TRANSPARENT)
        else
            find<EndGameFragment>(Pair(EndGameFragment::message, "Tie!"))
                .openModal(stageStyle = StageStyle.TRANSPARENT)
    }
}
