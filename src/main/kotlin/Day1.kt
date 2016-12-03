class Day1 {

  enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    fun turn(turn: Turn) =
        when (turn) {
          Turn.LEFT -> when (this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
          }
          Turn.RIGHT -> when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
          }
        }
  }

  enum class Turn {
    RIGHT,
    LEFT
  }

  data class State(val x: Int, val y: Int, val direction: Direction) {
    fun blocksAway() = Math.abs(x) + Math.abs(y)
  }

  data class Step(val turn: Turn, val blocks: Int)

  companion object {
    private fun parseInput(input: String) =
        input.split(", ")
            .map {
              val direction = if (it[0] == 'L') Turn.LEFT else Turn.RIGHT
              val blocks = it.drop(1).toInt()
              Step(direction, blocks)
            }

    fun blocksAway(input: String): Int = parseInput(input)
        .fold(State(0, 0, Direction.NORTH), { state, step -> applyStep(state, step) })
        .blocksAway()

    private fun applyStep(state: State, step: Step) =
        when (state.direction.turn(step.turn)) {
          Direction.NORTH -> State(state.x, state.y + step.blocks, Direction.NORTH)
          Direction.EAST -> State(state.x + step.blocks, state.y, Direction.EAST)
          Direction.SOUTH -> State(state.x, state.y - step.blocks, Direction.SOUTH)
          Direction.WEST -> State(state.x - step.blocks, state.y, Direction.WEST)
        }
  }
}