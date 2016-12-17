class Day17 {

  enum class Direction(val c: Char, val x: Int, val y: Int) {
    UP('U', 0, -1),
    DOWN('D', 0, 1),
    LEFT('L', -1, 0),
    RIGHT('R', 1, 0)
  }

  data class State(val x: Int, val y: Int, val moves: String)

  companion object {
    private val INDEX_TO_DIRECTION = arrayOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)

    fun isOpen(c: Char) = when (c) {
      'b', 'c', 'd', 'e', 'f' -> true
      else -> false
    }

    fun openDoors(md5: String) = md5.take(4)
        .mapIndexedNotNull { i, c -> if (isOpen(c)) INDEX_TO_DIRECTION[i] else null }

    fun solveMaze(passcode: String) = solveMaze(passcode, State(0, 0, ""))

    fun longestSolution(passcode: String) = longestSolution(passcode, State(0, 0, ""))

    private fun solveMaze(passcode: String, initial: State): String {
      val queue = mutableListOf(initial)

      while (queue.isNotEmpty()) {
        val state = queue.removeAt(0)

        if (state.x < 0 || state.x > 3 || state.y < 0 || state.y > 3) {
          continue
        }

        if (state.x == 3 && state.y == 3) {
          return state.moves
        }

        val nextMoves = openDoors("$passcode${state.moves}".md5(true))
            .mapNotNull { State(state.x + it.x, state.y + it.y, state.moves + it.c) }

        queue.addAll(nextMoves)
      }

      throw IllegalStateException("Impossible maze!")
    }

    // A sloppy copy and paste job, but it'll do
    private fun longestSolution(passcode: String, initial: State): Int {
      val queue = mutableListOf(initial)
      var longest: Int = 0

      while (queue.isNotEmpty()) {
        val state = queue.removeAt(0)

        if (state.x < 0 || state.x > 3 || state.y < 0 || state.y > 3) {
          continue
        }

        if (state.x == 3 && state.y == 3) {
          longest = Math.max(state.moves.length, longest)
          continue
        }

        val nextMoves = openDoors("$passcode${state.moves}".md5(true))
            .mapNotNull { State(state.x + it.x, state.y + it.y, state.moves + it.c) }

        queue.addAll(nextMoves)
      }

      return longest
    }
  }
}