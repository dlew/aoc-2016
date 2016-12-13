import java.util.*

class Day13 {

  data class Coord(val x: Int, val y: Int)

  companion object {
    private val ADJACENT = arrayOf(
        Coord(-1, 0),
        Coord(0, -1),
        Coord(1, 0),
        Coord(0, 1)
    )

    fun bfs(startX: Int, startY: Int, targetX: Int, targetY: Int, favorite: Int): Array<IntArray> {
      // Try an array 2x the size we need, just for simplicity
      val width = targetX * 2
      val height = targetY * 2
      val maze = generateMaze(width, height, favorite)

      val bfs = Array<IntArray>(height, { IntArray(width) })
      bfs[startY][startX] = 1

      var distance = 1
      val stack = ArrayList<Coord>()
      stack.add(Coord(startX, startY))
      while (stack.isNotEmpty()) {
        val nextStack = ArrayList<Coord>()

        stack.forEach { coord ->
          ADJACENT.forEach { rel ->
            val x = coord.x + rel.x
            val y = coord.y + rel.y

            if (x >= 0 && y >= 0 && x < width && y < height && !maze[y][x] && bfs[y][x] == 0) {
              bfs[y][x] = distance
              nextStack.add(Coord(x, y))
            }
          }
        }

        stack.clear()
        stack.addAll(nextStack)
        distance++
      }

      return bfs
    }

    fun maxSteps(bfs: Array<IntArray>, max: Int) =
        bfs.fold(0, { count, row ->
          count + row.fold(0, { subCount, item ->
            if (item != 0 && item <= max) subCount + 1 else subCount
          })
        })

    fun generateMaze(width: Int, height: Int, favorite: Int): Array<BooleanArray> {
      val maze = Array<BooleanArray>(height, { BooleanArray(width) })
      (0..width - 1).forEach { x ->
        (0..height - 1).forEach { y ->
          maze[y][x] = isWall(x, y, favorite)
        }
      }
      return maze
    }

    fun printMaze(maze: Array<BooleanArray>): String {
      val sb = StringBuilder()
      maze.forEach { row ->
        row.forEach { sb.append(if (it) "#" else ".") }
        sb.appendln()
      }
      return sb.toString().trim()
    }

    fun isWall(x: Int, y: Int, favorite: Int) = hammingWeight(math(x.toLong(), y.toLong(), favorite.toLong())) % 2 == 1

    private fun math(x: Long, y: Long, favorite: Long) = (x * x) + (3 * x) + (2 * x * y) + y + (y * y) + favorite

    private fun hammingWeight(num: Long): Int {
      var curr = num
      var count = 0
      while (curr != 0L) {
        if (curr.and(1L) != 0L) {
          count++
        }
        curr = curr.ushr(1)
      }
      return count
    }
  }

}