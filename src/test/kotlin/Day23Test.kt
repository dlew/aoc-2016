import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day23Test {

  @Test
  fun sample() {
    val input = Resources.toString(Resources.getResource("day23sample.txt"), Charsets.UTF_8)
    val registers = Day23.solve(input)
    assertEquals(3, registers['a'])
  }

  @Test
  fun part1() {
    val input = "cpy 7 a\n" + Resources.toString(Resources.getResource("day23.txt"), Charsets.UTF_8)
    val registers = Day23.solve(input)
    assertEquals(11640, registers['a'])
  }

  @Test
  fun part2() {
    val input = "cpy 12 a\n" + Resources.toString(Resources.getResource("day23.txt"), Charsets.UTF_8)
    val registers = Day23.solve(input)
    assertEquals(479008200, registers['a'])
  }
}