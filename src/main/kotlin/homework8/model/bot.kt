package homework8.model

data class Move(val row: Int, val column: Int)

interface Bot {
    fun makeMove(grid: Array<CharArray>): Move
}

class EasyBot : Bot {
    override fun makeMove(grid: Array<CharArray>) = getPossibleMoves(grid).random()

    private fun getPossibleMoves(grid: Array<CharArray>): List<Move> {
        val possibleMoves = mutableListOf<Move>()
        for (i in 0 until GameModel.GRID_SIZE) {
            for (j in 0 until GameModel.GRID_SIZE) {
                if (grid[i][j] == ' ')
                    possibleMoves.add(Move(i, j))
            }
        }
        return possibleMoves
    }
}
