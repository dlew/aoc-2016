import com.google.common.io.Resources
import org.junit.Assert.assertEquals
import org.junit.Test

class Day1Test {

  @Test
  fun sampleTest1() {
    assertEquals(5, Day1.blocksAway("R2, L3"))
  }

  @Test
  fun sampleTest2() {
    assertEquals(2, Day1.blocksAway("R2, R2, R2"))
  }

  @Test
  fun sampleTest3() {
    assertEquals(12, Day1.blocksAway("R5, L5, R5, R3"))
  }

  @Test
  fun solution() {
    val input = Resources.toString(Resources.getResource("day1.txt"), Charsets.UTF_8)
    assertEquals(242, Day1.blocksAway(input))
  }

  @Test
  fun sampleTest4() {
    assertEquals(4, Day1.firstStateVisitedTwice("R8, R4, R4, R8"))
  }

  @Test
  fun solutionPartTwo() {
    val input = Resources.toString(Resources.getResource("day1.txt"), Charsets.UTF_8)
    assertEquals(150, Day1.firstStateVisitedTwice(input))
  }
}