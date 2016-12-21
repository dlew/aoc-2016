import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day21Test {
  @Test
  fun sample() {
    assertEquals("ebcda", Day21.apply("abcde", "swap position 4 with position 0"))
    assertEquals("edcba", Day21.apply("ebcda", "swap letter d with letter b"))
    assertEquals("abcde", Day21.apply("edcba", "reverse positions 0 through 4"))
    assertEquals("bcdea", Day21.apply("abcde", "rotate left 1 step"))
    assertEquals("bdeac", Day21.apply("bcdea", "move position 1 to position 4"))
    assertEquals("abdec", Day21.apply("bdeac", "move position 3 to position 0"))
    assertEquals("ecabd", Day21.apply("abdec", "rotate based on position of letter b"))
    assertEquals("decab", Day21.apply("ecabd", "rotate based on position of letter d"))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day21.txt"), Charsets.UTF_8)
    val answer = input.split("\n").fold("abcdefgh", { password, input -> Day21.apply(password, input) })
    assertEquals("bgfacdeh", answer)
  }

  @Test
  fun sample2() {
    assertEquals("ecabd", Day21.undo("decab", "rotate based on position of letter d"))
    assertEquals("abdec", Day21.undo("ecabd", "rotate based on position of letter b"))
    assertEquals("bdeac", Day21.undo("abdec", "move position 3 to position 0"))
    assertEquals("bcdea", Day21.undo("bdeac", "move position 1 to position 4"))
    assertEquals("abcde", Day21.undo("bcdea", "rotate left 1 step"))
    assertEquals("edcba", Day21.undo("abcde", "reverse positions 0 through 4"))
    assertEquals("ebcda", Day21.undo("edcba", "swap letter d with letter b"))
    assertEquals("abcde", Day21.undo("ebcda", "swap position 4 with position 0"))
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day21.txt"), Charsets.UTF_8)
    val answer = input.split("\n").reversed().fold("fbgdceah", { password, input -> Day21.undo(password, input) })
    assertEquals("bdgheacf", answer)
  }
}