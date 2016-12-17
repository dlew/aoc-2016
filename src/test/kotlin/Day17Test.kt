import Day17.Direction
import Day17.Direction.*
import org.junit.Assert.assertEquals
import org.junit.Test

class Day17Test {
  @Test
  fun doors() {
    assertEquals(listOf(UP, DOWN, LEFT), Day17.openDoors("hijkl".md5(true)))
    assertEquals(listOf(UP, LEFT, RIGHT), Day17.openDoors("hijklD".md5(true)))
    assertEquals(emptyList<Direction>(), Day17.openDoors("hijklDR".md5(true)))
    assertEquals(listOf(RIGHT), Day17.openDoors("hijklDU".md5(true)))
  }

  @Test
  fun sample() {
    assertEquals("DDRRRD", Day17.solveMaze("ihgpwlah"))
    assertEquals("DDUDRLRRUDRD", Day17.solveMaze("kglvqrro"))
    assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", Day17.solveMaze("ulqzkmiv"))
  }

  @Test
  fun part1() {
    assertEquals("RDURRDDLRD", Day17.solveMaze("awrkjxxr"))
  }

  @Test
  fun sample2() {
    assertEquals(370, Day17.longestSolution("ihgpwlah"))
    assertEquals(492, Day17.longestSolution("kglvqrro"))
    assertEquals(830, Day17.longestSolution("ulqzkmiv"))
  }

  @Test
  fun part2() {
    assertEquals(526, Day17.longestSolution("awrkjxxr"))
  }
}