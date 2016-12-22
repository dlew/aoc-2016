import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day22Test {
  @Test
  fun aEmpty() {
    val a = Day22.parseNode("/dev/grid/node-x15-y24   94T    0T    94T    0%")
    val b = Day22.parseNode("/dev/grid/node-x10-y10   94T   69T    25T   73%")
    assertFalse(Day22.viable(a, b))
  }

  @Test
  fun sameNode() {
    val a = Day22.parseNode("/dev/grid/node-x15-y24   94T   69T    25T   73%")
    val b = Day22.parseNode("/dev/grid/node-x15-y24  100T   10T    90T   10%")
    assertFalse(Day22.viable(a, b))
  }

  @Test
  fun noSpace() {
    val a = Day22.parseNode("/dev/grid/node-x15-y24   94T   69T    25T   73%")
    val b = Day22.parseNode("/dev/grid/node-x10-y10   70T   60T    10T   86%")
    assertFalse(Day22.viable(a, b))
  }

  @Test
  fun viable() {
    val a = Day22.parseNode("/dev/grid/node-x15-y24   94T   69T    25T   73%")
    val b = Day22.parseNode("/dev/grid/node-x10-y10  100T   10T    90T   10%")
    assertTrue(Day22.viable(a, b))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day22.txt"), Charsets.UTF_8)
    val nodes = Day22.parseNodes(input)
    assertEquals(924, Day22.countViablePairs(nodes))
  }

  @Test
  fun sample2() {
    val input = Resources.toString(Resources.getResource("day22sample.txt"), Charsets.UTF_8)
    val nodes = Day22.parseNodes(input)
    assertEquals(7, Day22.finalSolution(nodes))
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day22.txt"), Charsets.UTF_8)
    val nodes = Day22.parseNodes(input)
    assertEquals(213, Day22.finalSolution(nodes))
  }
}