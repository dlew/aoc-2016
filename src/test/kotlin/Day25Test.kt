import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day25Test {
  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day25.txt"), Charsets.UTF_8)
    assertEquals(1, Day25.lowestForClock(input))
  }
}