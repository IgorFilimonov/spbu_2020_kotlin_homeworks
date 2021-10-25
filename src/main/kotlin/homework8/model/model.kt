package homework8.model

import homework8.GameController
import homework8.GameStages

class GameModel(private val controller: GameController) {
    companion object {
        const val GRID_SIZE = 3
    }

    private val grid = Array(GRID_SIZE) { CharArray(GRID_SIZE) { ' ' } }
    var movesAmount = 0
    private val bot = if (controller.isBotEasy) EasyBot() else EasyBot()

    fun makeMove(row: Int, column: Int) {
        grid[row][column] = if (movesAmount % 2 == 0) 'X' else 'O'
        movesAmount++

        val stage = getStage()
        if (stage != GameStages.ONGOING) {
            controller.finishGame(stage)
        } else {
            triggerBot()
        }
    }

    private fun getStage(): GameStages {
        for (i in 0 until GRID_SIZE) {
            var isDifference = false
            for (j in 1 until GRID_SIZE) {
                if (grid[i][j] != grid[i][0]) {
                    isDifference = true
                    break
                }
            }
            if (!isDifference && grid[i][0] != ' ') {
                return GameStages.WIN_OR_LOSE
            }
        }
        for (i in 0 until GRID_SIZE) {
            var isDifference = false
            for (j in 1 until GRID_SIZE) {
                if (grid[j][i] != grid[0][i]) {
                    isDifference = true
                    break
                }
            }
            if (!isDifference && grid[0][i] != ' ') {
                return GameStages.WIN_OR_LOSE
            }
        }
        for (i in 1 until GRID_SIZE) {
            if (grid[i][i] == grid[0][0]) {
                if (grid[0][0] != ' ' && i == GRID_SIZE - 1) {
                    return GameStages.WIN_OR_LOSE
                }
            } else {
                break
            }
        }
        for (i in 1 until GRID_SIZE) {
            if (grid[i][GRID_SIZE - 1 - i] == grid[0][GRID_SIZE - 1]) {
                if (grid[0][GRID_SIZE - 1] != ' ' && i == GRID_SIZE - 1) {
                    return GameStages.WIN_OR_LOSE
                }
            } else {
                break
            }
        }
        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                if (grid[i][j] == ' ') {
                    return GameStages.ONGOING
                }
            }
        }
        return GameStages.TIE
    }

    fun triggerBot() {
        if (controller.isBotEnabled) {
            if (movesAmount % 2 == 0 && controller.botSide == 'X'
                || movesAmount % 2 == 1 && controller.botSide == 'O'
            ) {
                val move = bot.makeMove(grid)
                controller.makeMove(move.row, move.column)
            }
        }
    }
}
