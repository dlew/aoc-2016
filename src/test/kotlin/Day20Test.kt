import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day20Test {
  @Test
  fun sample() {
    val input = Resources.toString(Resources.getResource("day20sample.txt"), Charsets.UTF_8)
    assertEquals(3, Day20.findLowest(input))
  }

  @Test
  fun cornerCase() {
    val input = "0-2\n3-4"
    assertEquals(5, Day20.findLowest(input))
  }

  @Test
  fun cornerCase2() {
    val input = "0-10\n3-4"
    assertEquals(11, Day20.findLowest(input))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day20.txt"), Charsets.UTF_8)
    assertEquals(31053880, Day20.findLowest(input))
  }

  @Test
  fun sample2() {
    val input = Resources.toString(Resources.getResource("day20sample.txt"), Charsets.UTF_8)
    assertEquals(2, Day20.count(input, 9))
  }

  @Test
  fun cornerCase3() {
    val input = Resources.toString(Resources.getResource("day20sample.txt"), Charsets.UTF_8)
    assertEquals(8, Day20.count(input, 15))
  }

  @Test
  fun cornerCase4() {
    val input = "0-5\n7-10"
    assertEquals(1, Day20.count(input, 10))
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day20.txt"), Charsets.UTF_8)
    assertEquals(117, Day20.count(input, 4294967295L))
    // 233 is too high
  }
}