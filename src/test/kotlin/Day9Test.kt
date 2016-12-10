import com.google.common.io.Resources
import org.junit.Assert.assertEquals
import org.junit.Test

class Day9Test {
  @Test
  fun sample1() {
    assertEquals("ADVENT", Day9.decompress("ADVENT", false))
    assertEquals(6, Day9.decompressLength("ADVENT", false))
  }

  @Test
  fun sample2() {
    assertEquals("ABBBBBC", Day9.decompress("A(1x5)BC", false))
    assertEquals(7, Day9.decompressLength("A(1x5)BC", false))
  }

  @Test
  fun sample3() {
    assertEquals("XYZXYZXYZ", Day9.decompress("(3x3)XYZ", false))
    assertEquals(9, Day9.decompressLength("(3x3)XYZ", false))
  }

  @Test
  fun sample4() {
    assertEquals("ABCBCDEFEFG", Day9.decompress("A(2x2)BCD(2x2)EFG", false))
    assertEquals(11, Day9.decompressLength("A(2x2)BCD(2x2)EFG", false))
  }

  @Test
  fun sample5() {
    assertEquals("(1x3)A", Day9.decompress("(6x1)(1x3)A", false))
    assertEquals(6, Day9.decompressLength("(6x1)(1x3)A", false))

  }

  @Test
  fun sample6() {
    assertEquals("X(3x3)ABC(3x3)ABCY", Day9.decompress("X(8x2)(3x3)ABCY", false))
    assertEquals(18, Day9.decompressLength("X(8x2)(3x3)ABCY", false))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day9.txt"), Charsets.UTF_8)
    assertEquals(97714, Day9.decompressLength(input, false))
  }

  @Test
  fun sample1v2() {
    assertEquals("XYZXYZXYZ", Day9.decompress("(3x3)XYZ", true))
    assertEquals(9, Day9.decompressLength("(3x3)XYZ", true))
  }

  @Test
  fun sample2v2() {
    assertEquals("XABCABCABCABCABCABCY", Day9.decompress("X(8x2)(3x3)ABCY", true))
    assertEquals(20, Day9.decompressLength("X(8x2)(3x3)ABCY", true))
  }

  @Test
  fun sample3v2() {
    assertEquals("A".repeat(241920), Day9.decompress("(27x12)(20x12)(13x14)(7x10)(1x12)A", true))
    assertEquals(241920, Day9.decompressLength("(27x12)(20x12)(13x14)(7x10)(1x12)A", true))
  }

  @Test
  fun sample4v2() {
    assertEquals(445, Day9.decompressLength("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN", true))
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day9.txt"), Charsets.UTF_8)
    assertEquals(10762972461, Day9.decompressLength(input, true))
  }
}