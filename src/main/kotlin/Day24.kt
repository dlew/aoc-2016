import java.util.*

class Day24 constructor(input: String) {

  data class Coord(val x: Int, val y: Int)

  data class Wire(val num: Int, val loc: Coord)

  data class Path(val from: Int, val to: Int)

  val maze: List<List<Boolean>>
  val wires: List<Wire>
  val distances: Map<Path, Int>

  init {
    val wireBuilder = ArrayList<Wire>()

    maze = input
        .split('\n')
        .mapIndexed { y, row ->
          row.mapIndexed { x, item ->
            when (item) {
              '#' -> true
              '.' -> false
              else -> {
                wireBuilder.add(Wire(Integer.valueOf(item.toString()), Coord(x, y)))
                false
              }
            }
          }
        }

    wires = wireBuilder.sortedBy { it.num }

    // Pre-calculate all distances between wires
    distances = HashMap<Path, Int>()
    (0..wires.size - 1).forEach { a ->
      (a + 1..wires.size - 1).forEach { b ->
        val fastest = fastestPath(wires[a].loc, wires[b].loc)
        distances[Path(wires[a].num, wires[b].num)] = fastest
        distances[Path(wires[b].num, wires[a].num)] = fastest
      }
    }
  }

  fun fastestSolution(returnToStart: Boolean): Int {
    return fastest(wires[0], wires - wires[0], returnToStart)
  }

  private fun fastest(from: Wire, remainingWires: List<Wire>, returnToStart: Boolean): Int {
    if (remainingWires.isEmpty()) {
      if (returnToStart) {
        return distances[Path(from.num, 0)] ?: throw IllegalStateException()
      }
      else {
        return 0
      }
    }

    var fastest = Integer.MAX_VALUE
    remainingWires.forEach { to ->
      val distance = distances[Path(from.num, to.num)] ?: throw IllegalStateException()
      val theRest = fastest(to, remainingWires - to, returnToStart)

      fastest = Math.min(fastest, distance + theRest)
    }

    return fastest
  }

  fun fastestPath(start: Coord, end: Coord): Int {
    val visited = HashSet<Coord>()
    visited.add(start)

    var lastVisited = listOf(start)
    var count = 1
    while (lastVisited.isNotEmpty()) {
      val possibilities = lastVisited
          .flatMap { legalAdjacent(it) }
          .toSet()
          .filter { !visited.contains(it) }

      if (possibilities.contains(end)) {
        return count
      }

      lastVisited = possibilities
      visited.addAll(lastVisited)
      count++
    }

    throw IllegalStateException("Should have found a solution!")
  }

  fun legalAdjacent(point: Coord): List<Coord> {
    val possibilities = ArrayList<Coord>()

    // Up
    if (!maze[point.y - 1][point.x]) {
      possibilities.add(Coord(point.x, point.y - 1))
    }

    // Down
    if (!maze[point.y + 1][point.x]) {
      possibilities.add(Coord(point.x, point.y + 1))
    }

    // Left
    if (!maze[point.y][point.x - 1]) {
      possibilities.add(Coord(point.x - 1, point.y))
    }

    // Right
    if (!maze[point.y][point.x + 1]) {
      possibilities.add(Coord(point.x + 1, point.y))
    }

    return possibilities
  }
}