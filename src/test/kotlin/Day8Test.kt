import com.google.common.io.Resources
import junit.framework.Assert.assertEquals
import org.junit.Test

class Day8Test {

  @Test
  fun parser() {
    assertEquals(Day8.Rect(3, 2), Day8.parseCommand("rect 3x2"))
    assertEquals(Day8.RotateRow(0, 4), Day8.parseCommand("rotate row y=0 by 4"))
    assertEquals(Day8.RotateColumn(1, 1), Day8.parseCommand("rotate column x=1 by 1"))
  }

  @Test
  fun sample() {
    val screen = Day8.createScreen(7, 3)

    val screen2 = Day8.applyCommand(screen, Day8.Rect(3, 2))
    val match2 = Day8.createScreen(7, 3)
    match2.grid[0][0] = true
    match2.grid[0][1] = true
    match2.grid[0][2] = true
    match2.grid[1][0] = true
    match2.grid[1][1] = true
    match2.grid[1][2] = true
    assertEquals(Day8.printScreen(match2), Day8.printScreen(screen2))

    val screen3 = Day8.applyCommand(screen2, Day8.RotateColumn(1, 1))
    val match3 = Day8.createScreen(7, 3)
    match3.grid[0][0] = true
    match3.grid[0][2] = true
    match3.grid[1][0] = true
    match3.grid[1][1] = true
    match3.grid[1][2] = true
    match3.grid[2][1] = true
    assertEquals(Day8.printScreen(match3), Day8.printScreen(screen3))

    val screen4 = Day8.applyCommand(screen3, Day8.RotateRow(0, 4))
    val match4 = Day8.createScreen(7, 3)
    match4.grid[0][4] = true
    match4.grid[0][6] = true
    match4.grid[1][0] = true
    match4.grid[1][1] = true
    match4.grid[1][2] = true
    match4.grid[2][1] = true
    assertEquals(Day8.printScreen(match4), Day8.printScreen(screen4))

    val screen5 = Day8.applyCommand(screen4, Day8.RotateColumn(1, 1))
    val match5 = Day8.createScreen(7, 3)
    match5.grid[0][1] = true
    match5.grid[0][4] = true
    match5.grid[0][6] = true
    match5.grid[1][0] = true
    match5.grid[1][2] = true
    match5.grid[2][1] = true
    assertEquals(Day8.printScreen(match5), Day8.printScreen(screen5))

    assertEquals(6, Day8.countLit(screen5))
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day8.txt"), Charsets.UTF_8)

    val result = input.split('\n')
        .map { Day8.parseCommand(it) }
        .fold(Day8.createScreen(50, 6), { screen, command -> Day8.applyCommand(screen, command) })

    assertEquals(116, Day8.countLit(result))

    println(Day8.printScreen(result))
  }
}