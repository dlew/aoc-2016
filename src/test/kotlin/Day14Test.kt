import Day14.Companion.indexOfKey
import org.junit.Assert.*
import org.junit.Test

class Day14Test {

  @Test
  fun components() {
    assertEquals('8', Day14.findRepeat(Day14.generate("abc", 18), 3))
    (19..1018).forEach { index ->
      assertNull(Day14.findRepeat(Day14.generate("abc", index), 5, '8'))
    }

    assertEquals('e', Day14.findRepeat(Day14.generate("abc", 39), 3))
    assertEquals('e', Day14.findRepeat(Day14.generate("abc", 816), 5, 'e'))

    assertEquals('d', Day14.findRepeat(Day14.generate("abc", 45), 3))
    (46..1045).forEach { index ->
      assertNull(Day14.findRepeat(Day14.generate("abc", index), 5, 'd'))
    }

    assertEquals('9', Day14.findRepeat(Day14.generate("abc", 92), 3))
    assertEquals('9', Day14.findRepeat(Day14.generate("abc", 200), 5, '9'))
  }

  @Test
  fun sample() {
    assertEquals(92, indexOfKey("abc", 2))
    assertEquals(22728, indexOfKey("abc", 64))
  }

  @Test
  fun part1() {
    assertEquals(23890, indexOfKey("ahsbgdzn", 64))
  }

  @Test
  fun components2() {
    assertEquals('2', Day14.findRepeat(Day14.generate("abc", 5, 2017), 3))

    assertEquals('e', Day14.findRepeat(Day14.generate("abc", 10, 2017), 3))
    assertEquals('e', Day14.findRepeat(Day14.generate("abc", 89, 2017), 5, 'e'))
  }

  @Test
  fun sample2() {
    assertEquals(22551, indexOfKey("abc", 64, 2017))
  }

  @Test
  fun part2() {
    assertEquals(22696, indexOfKey("ahsbgdzn", 64, 2017))
  }
}