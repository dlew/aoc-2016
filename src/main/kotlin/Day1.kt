class Day1 {

  enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    fun turn(turn: Turn) =
        when (turn) {
          Turn.NONE -> this
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
    NONE,
    RIGHT,
    LEFT
  }

  data class Location(val x: Int, val y: Int) {
    fun blocksAway() = Math.abs(x) + Math.abs(y)
  }

  data class State(val loc: Location, val direction: Direction)

  data class Step(val turn: Turn, val blocks: Int) {
    // Turns things like R3 into R1 N1 N1
    fun stepByStep(): Iterable<Step> = (1..blocks).asIterable()
        .map { if (it == 1) Step(turn, 1) else Step(Turn.NONE, 1) }
  }

  companion object {
    private fun parseInput(input: String) =
        input.split(", ")
            .map {
              val direction = if (it[0] == 'L') Turn.LEFT else Turn.RIGHT
              val blocks = it.drop(1).toInt()
              Step(direction, blocks)
            }

    fun blocksAway(input: String) = parseInput(input)
        .fold(State(Location(0, 0), Direction.NORTH), { state, step -> applyStep(state, step) })
        .loc.blocksAway()

    fun firstStateVisitedTwice(input: String) = parseInput(input)
        .flatMap { it.stepByStep() }
        .scan(State(Location(0, 0), Direction.NORTH), { state, step -> applyStep(state, step) })
        .map { it.loc }
        .fold(Pair<Set<Location>, Location?>(emptySet(), null), { p, location ->
          if (p.second != null) {
            p
          } else if (location in p.first) {
            Pair(p.first, location)
          } else {
            Pair(p.first.plusElement(location), null)
          }
        })
        .second?.blocksAway()

    private fun applyStep(state: State, step: Step) =
        when (state.direction.turn(step.turn)) {
          Direction.NORTH -> State(Location(state.loc.x, state.loc.y + step.blocks), Direction.NORTH)
          Direction.EAST -> State(Location(state.loc.x + step.blocks, state.loc.y), Direction.EAST)
          Direction.SOUTH -> State(Location(state.loc.x, state.loc.y - step.blocks), Direction.SOUTH)
          Direction.WEST -> State(Location(state.loc.x - step.blocks, state.loc.y), Direction.WEST)
        }
  }
}