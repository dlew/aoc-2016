import org.junit.Assert.*
import org.junit.Test

class Day16Test {
  @Test
  fun generator() {
    assertEquals("100", Day16.generate("1"))
    assertEquals("001", Day16.generate("0"))
    assertEquals("11111000000", Day16.generate("11111"))
    assertEquals("1111000010100101011110000", Day16.generate("111100001010"))
  }

  @Test
  fun checksum() {
    assertEquals("100", Day16.checksum("110010110100"))
  }

  @Test
  fun sample() {
    assertEquals("01100", Day16.scramble("10000", 20))
  }

  @Test
  fun part1() {
    assertEquals("10010110010011110", Day16.scramble("10010000000110000", 272))
  }

  @Test
  fun part2() {
    assertEquals("01101011101100011", Day16.scramble("10010000000110000", 35651584))
  }
}