import Day15.Disc
import org.junit.Assert.assertEquals
import org.junit.Test

class Day15Test {
  @Test
  fun sample() {
    assertEquals(5L, Day15.firstWinner(listOf(
        Disc(5, 4),
        Disc(2, 1)
    )))
  }

  @Test
  fun part1() {
    assertEquals(376777, Day15.firstWinner(listOf(
        Disc(13, 1),
        Disc(19, 10),
        Disc(3, 2),
        Disc(7, 1),
        Disc(5, 3),
        Disc(17, 5)
    )))
  }

  @Test
  fun part2() {
    assertEquals(3903937, Day15.firstWinner(listOf(
        Disc(13, 1),
        Disc(19, 10),
        Disc(3, 2),
        Disc(7, 1),
        Disc(5, 3),
        Disc(17, 5),
        Disc(11, 0)
    )))
  }
}