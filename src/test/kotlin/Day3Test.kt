import com.google.common.io.Resources
import org.junit.Assert.assertEquals
import org.junit.Test

class Day3Test {
  @Test
  fun sample1() {
    val input = "  5 10 25"
    assertEquals(0, Day3.numValidTriangles(input))
  }

  @Test
  fun solution() {
    val input = Resources.toString(Resources.getResource("day3.txt"), Charsets.UTF_8)
    assertEquals(993, Day3.numValidTriangles(input))
    assertEquals(1849, Day3.numValidTrianglesTranspose(input))
  }
}