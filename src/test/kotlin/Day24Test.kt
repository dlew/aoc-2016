import Day24.Coord
import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day24Test {
  @Test
  fun fastestPath() {
    val input = Resources.toString(Resources.getResource("day24sample.txt"), Charsets.UTF_8)
    val maze = Day24(input)
    assertEquals(2, maze.fastestPath(Coord(1, 1), Coord(3, 1)))
    assertEquals(2, maze.fastestPath(Coord(1, 1), Coord(1, 3)))
    assertEquals(10, maze.fastestPath(Coord(9, 1), Coord(1, 3)))
  }

  @Test
  fun sample() {
    val input = Resources.toString(Resources.getResource("day24sample.txt"), Charsets.UTF_8)
    val maze = Day24(input)
    assertEquals(14, maze.fastestSolution(false))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day24.txt"), Charsets.UTF_8)
    val maze = Day24(input)
    assertEquals(464, maze.fastestSolution(false))
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day24.txt"), Charsets.UTF_8)
    val maze = Day24(input)
    assertEquals(464, maze.fastestSolution(true))
  }
}