import org.junit.Assert.assertEquals
import org.junit.Test

class Day18Test {

  @Test
  fun sample() {
    assertEquals(
        Day18.parseRow(".^^^^"),
        Day18.nextRow(Day18.parseRow("..^^."))
    )

    assertEquals(
        Day18.parseRow("^^..^"),
        Day18.nextRow(Day18.parseRow(".^^^^"))
    )

    assertEquals(38, Day18.countSafeTiles(Day18.parseRow(".^^.^.^^^^"), 10))
  }

  @Test
  fun part1() {
    val input = "^.^^^..^^...^.^..^^^^^.....^...^^^..^^^^.^^.^^^^^^^^.^^.^^^^...^^...^^^^.^.^..^^..^..^.^^.^.^......."
    assertEquals(1913, Day18.countSafeTiles(Day18.parseRow(input), 40))
  }

  @Test
  fun part2() {
    val input = "^.^^^..^^...^.^..^^^^^.....^...^^^..^^^^.^^.^^^^^^^^.^^.^^^^...^^...^^^^.^.^..^^..^..^.^^.^.^......."
    assertEquals(19993564, Day18.countSafeTiles(Day18.parseRow(input), 400000))
  }
}