import org.junit.Assert.*
import org.junit.Test

class Day19Test {
  @Test
  fun sample() {
    assertEquals(3, Day19.solve(5))
  }

  @Test
  fun part1() {
    assertEquals(1842613, Day19.solve(3018458))
  }

  @Test
  fun sample2() {
    assertEquals(2, Day19.solve2(5))
  }

  @Test
  fun part2() {
    assertEquals(1424135, Day19.solve2(3018458))
  }
}
