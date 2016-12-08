import com.google.common.io.Resources
import junit.framework.Assert.assertEquals
import org.junit.Test

class Day2Test {
  @Test
  fun sample() {
    val input = """
      ULL
      RRDDD
      LURDL
      UUUUD
    """.trimIndent()

    assertEquals("1985", Day2.decode(Day2.NUMBER_GRID, Day2.Location(1, 1), input))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day2.txt"), Charsets.UTF_8)
    assertEquals("47978", Day2.decode(Day2.NUMBER_GRID, Day2.Location(1, 1), input))
  }

  @Test
  fun samplePart2() {
    val input = """
      ULL
      RRDDD
      LURDL
      UUUUD
    """.trimIndent()

    assertEquals("5DB3", Day2.decode(Day2.HARD_GRID, Day2.Location(0, 2), input))
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day2.txt"), Charsets.UTF_8)
    assertEquals("659AD", Day2.decode(Day2.HARD_GRID, Day2.Location(0, 2), input))
  }
}