import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day12Test {
  @Test
  fun sample() {
    val input = Resources.toString(Resources.getResource("day12sample.txt"), Charsets.UTF_8)
    val registers = Day12.solve(input)
    assertEquals(42, registers['a'])
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day12.txt"), Charsets.UTF_8)
    val registers = Day12.solve(input)
    assertEquals(317993, registers['a'])
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day12.txt"), Charsets.UTF_8)
    val registers = Day12.solve2(input)
    assertEquals(9227647, registers['a'])
  }
}
