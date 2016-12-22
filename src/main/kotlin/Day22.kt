import java.util.*
import java.util.regex.Pattern

class Day22 {

  data class Coord(val x: Int, val y: Int)

  data class Node(val x: Int, val y: Int, val size: Int, val used: Int, val avail: Int)

  companion object {
    private val NODE_PATTERN =
        Pattern.compile("/dev/grid/node-x(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)%")

    fun parseNodes(input: String) = input.split("\n").map { parseNode(it) }

    fun parseNode(input: String): Node {
      val matcher = NODE_PATTERN.matcher(input)
      matcher.matches()
      return Node(
          matcher.group(1).toInt(),
          matcher.group(2).toInt(),
          matcher.group(3).toInt(),
          matcher.group(4).toInt(),
          matcher.group(5).toInt()
      )
    }

    fun nodesToGrid(nodes: List<Node>, transform: (Node) -> Int): List<List<Int>> {
      val result = ArrayList<MutableList<Int>>()
      nodes.forEach { node ->
        if (result.size < node.y + 1) {
          result.add(ArrayList<Int>())
        }
        result[node.y].add(transform.invoke(node))
      }
      return result
    }

    fun viable(a: Node, b: Node): Boolean {
      return a.used != 0
          && (a.x != b.x || a.y != b.y)
          && a.used <= b.avail
    }

    fun countViablePairs(nodes: List<Node>): Int {
      var count = 0
      nodes.forEach { a ->
        nodes.forEach { b ->
          if (viable(a, b)) {
            count++
          }
        }
      }
      return count
    }

    // Key observation: Besides a few nodes which have a huge used
    // area, all nodes can move into/out of the empty node. So we just
    // convert this into a maze with a few walls
    private fun convertToMaze(nodes: List<Node>): List<List<Boolean>> {
      val result = ArrayList<MutableList<Boolean>>()
      nodes.forEach { node ->
        if (result.size < node.y + 1) {
          result.add(ArrayList<Boolean>())
        }
        result[node.y].add(node.used > 100)
      }
      return result
    }

    fun finalSolution(nodes: List<Node>): Int {
      val maze = convertToMaze(nodes)
      val width = maze[0].size
      val emptyNode = findEmpty(nodes)

      var empty = Coord(emptyNode.x, emptyNode.y)
      var steps = 0
      (1..width - 1).reversed().forEach { dataIndex ->
        val mazeWithDataBlocked = maze.map { it.toMutableList() }
        mazeWithDataBlocked[0][dataIndex] = true

        steps += fastestPath(mazeWithDataBlocked, empty, Coord(dataIndex - 1, 0)) + 1
        empty = Coord(dataIndex, 0)
      }

      return steps
    }

    fun fastestPath(maze: List<List<Boolean>>, start: Coord, end: Coord): Int {
      val visited = HashSet<Coord>()
      visited.add(start)

      var lastVisited = listOf(start)
      var count = 1
      while (lastVisited.isNotEmpty()) {
        val possibilities = lastVisited
            .flatMap { legalAdjacent(maze, it) }
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

    fun legalAdjacent(maze: List<List<Boolean>>, point: Coord): List<Coord> {
      val possibilities = ArrayList<Coord>()

      // Up
      if (point.y != 0 && !maze[point.y - 1][point.x]) {
        possibilities.add(Coord(point.x, point.y - 1))
      }

      // Down
      if (point.y != maze.size - 1 && !maze[point.y + 1][point.x]) {
        possibilities.add(Coord(point.x, point.y + 1))
      }

      // Left
      if (point.x != 0 && !maze[point.y][point.x - 1]) {
        possibilities.add(Coord(point.x - 1, point.y))
      }

      // Right
      if (point.x != maze[0].size - 1 && !maze[point.y][point.x + 1]) {
        possibilities.add(Coord(point.x + 1, point.y))
      }

      return possibilities
    }

    fun findEmpty(nodes: List<Node>) = nodes.first { it.used == 0 }
  }
}