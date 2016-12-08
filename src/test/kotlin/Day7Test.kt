import com.google.common.io.Resources
import junit.framework.Assert.*
import org.junit.Test

class Day7Test {

  @Test
  fun sampleTLS() {
    assertTrue(Day7.address("abba[mnop]qrst").supportsTLS())
    assertFalse(Day7.address("abcd[bddb]xyyx").supportsTLS())
    assertFalse(Day7.address("aaaa[qwer]tyui").supportsTLS())
    assertTrue(Day7.address("ioxxoj[asdfgh]zxcvbn").supportsTLS())
  }

  @Test
  fun solution() {
    val input = Resources.toString(Resources.getResource("day7.txt"), Charsets.UTF_8)

    assertEquals(110, input.split('\n')
        .map { Day7.address(it) }
        .filter { it.supportsTLS() }
        .count())
  }

  @Test
  fun sampleSSL() {
    assertTrue(Day7.address("aba[bab]xyz").supportsSSL())
    assertFalse(Day7.address("xyx[xyx]xyx").supportsSSL())
    assertTrue(Day7.address("aaa[kek]eke").supportsSSL())
    assertTrue(Day7.address("zazbz[bzb]cdb").supportsSSL())
  }

  @Test
  fun solutionPart2() {
    val input = Resources.toString(Resources.getResource("day7.txt"), Charsets.UTF_8)

    assertEquals(242, input.split('\n')
        .map { Day7.address(it) }
        .filter { it.supportsSSL() }
        .count())
  }
}