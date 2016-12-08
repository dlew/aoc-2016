import com.google.common.io.Resources
import org.junit.Assert
import org.junit.Test

class Day6Test {
  @Test
  fun sample() {
    val input = Resources.toString(Resources.getResource("day6sample.txt"), Charsets.UTF_8)
    Assert.assertEquals("easter", Day6.decrypt(input))
    Assert.assertEquals("advent", Day6.decrypt(input, false))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day6.txt"), Charsets.UTF_8)
    Assert.assertEquals("mlncjgdg", Day6.decrypt(input))
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day6.txt"), Charsets.UTF_8)
    Assert.assertEquals("mlncjgdg", Day6.decrypt(input))
    Assert.assertEquals("bipjaytb", Day6.decrypt(input, false))
  }
}