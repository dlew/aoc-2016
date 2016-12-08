import Day5.Companion.md5
import junit.framework.Assert.assertEquals
import org.junit.Test

class Day5Test {

  @Test
  fun passwordChar() {
    assertEquals('1', Day5.passwordChar(Day5.md5("abc3231929")))
  }

  @Test
  fun passwordCharHard() {
    assertEquals(Day5.PassChar('5', 1), Day5.passwordCharHard(Day5.md5("abc3231929")))
  }

  @Test
  fun nextIndex() {
    assertEquals(3231929, Day5.nextPasswordIndex("abc", 0))
  }

  @Test
  fun sample() {
    assertEquals("18f47a30", Day5.findPassword("abc", 8))
  }

  @Test
  fun solution() {
    assertEquals("d4cd2ee1", Day5.findPassword("ugkcyxxp", 8))
  }

  @Test
  fun sampleHard() {
    assertEquals("05ace8e3", Day5.findPasswordHard("abc", 8))
  }

  @Test
  fun solutionHard() {
    assertEquals("f2c730e5", Day5.findPasswordHard("ugkcyxxp", 8))
  }

}