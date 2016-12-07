class Day2 {

  enum class Move {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

  data class Location(val x: Int, val y: Int)

  companion object {
    val INV = 'Z' // Invalid character

    val NUMBER_GRID = arrayOf(
        arrayOf('1', '2', '3'),
        arrayOf('4', '5', '6'),
        arrayOf('7', '8', '9')
    )

    val HARD_GRID = arrayOf(
        arrayOf(INV, INV, '1', INV, INV),
        arrayOf(INV, '2', '3', '4', INV),
        arrayOf('5', '6', '7', '8', '9'),
        arrayOf(INV, 'A', 'B', 'C', INV),
        arrayOf(INV, INV, 'D', INV, INV)
    )

    private fun parseInput(input: String): List<List<Move>> =
        input.split('\n')
            .map {
              it.map { c ->
                when (c) {
                  'U' -> Move.UP
                  'D' -> Move.DOWN
                  'L' -> Move.LEFT
                  'R' -> Move.RIGHT
                  else -> throw IllegalArgumentException("Invalid input character: '$c'")
                }
              }
            }

    fun decode(grid: Array<Array<Char>>, start: Location, input: String) =
        parseInput(input)
            .fold(listOf(start), { state, moves -> state.plusElement(executeMoves(grid, state.last(), moves)) })
            .drop(1)
            .fold("", { text, loc -> text + grid[loc.y][loc.x] })

    private fun executeMoves(grid: Array<Array<Char>>, start: Location, moves: List<Move>) =
        moves.fold(start, { location, move -> executeMove(grid, location, move) })

    private fun executeMove(grid: Array<Array<Char>>, loc: Location, move: Move): Location =
        when (move) {
          Move.UP -> {
            val newY = loc.y - 1
            if (newY < 0 || grid[loc.x][newY] == INV) loc else Location(loc.x, newY)
          }
          Move.DOWN -> {
            val newY = loc.y + 1
            if (newY >= grid[loc.x].size || grid[loc.x][newY] == INV) loc else Location(loc.x, newY)
          }
          Move.LEFT -> {
            val newX = loc.x - 1
            if (newX < 0 || grid[newX][loc.y] == INV) loc else Location(newX, loc.y)
          }
          Move.RIGHT -> {
            val newX = loc.x + 1
            if (newX >= grid.size || grid[newX][loc.y] == INV) loc else Location(newX, loc.y)
          }
        }
  }
}