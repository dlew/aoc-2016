import com.google.common.io.Resources
import org.junit.Assert.assertEquals
import org.junit.Test

class Day13Test {

  @Test
  fun maze() {
    val input = Resources.toString(Resources.getResource("day13maze.txt"), Charsets.UTF_8)
    val maze = Day13.generateMaze(10, 7, 10)
    assertEquals(input, Day13.printMaze(maze))
  }

  @Test
  fun sample() {
    val bfs = Day13.bfs(1, 1, 7, 4, 10)
    assertEquals(11, bfs[4][7])
  }

  @Test
  fun part1() {
    val bfs = Day13.bfs(1, 1, 31, 39, 1352)
    assertEquals(90, bfs[39][31])
  }

  @Test
  fun part2() {
    val bfs = Day13.bfs(1, 1, 31, 39, 1352)
    assertEquals(135, Day13.maxSteps(bfs, 50))
  }
}
