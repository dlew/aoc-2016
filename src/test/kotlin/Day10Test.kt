import Day10.*
import Day10.Target
import com.google.common.io.Resources
import org.junit.Assert.assertEquals
import org.junit.Test

class Day10Test {
  @Test
  fun sample() {
    val input = Resources.toString(Resources.getResource("day10sample.txt"), Charsets.UTF_8)
    val result = Day10.execute(input)

    val moves = result.moves
    val comparisons = result.comparisons

    assertEquals(Move(Target(Type.BOT, 2), 5), moves[0])
    assertEquals(Move(Target(Type.BOT, 1), 3), moves[1])
    assertEquals(Move(Target(Type.BOT, 2), 2), moves[2])

    assertEquals(Comparison(2, 2, 5), comparisons[0])
    assertEquals(Move(Target(Type.BOT, 1), 2), moves[3])
    assertEquals(Move(Target(Type.BOT, 0), 5), moves[4])

    assertEquals(Comparison(1, 2, 3), comparisons[1])
    assertEquals(Move(Target(Type.OUTPUT, 1), 2), moves[5])
    assertEquals(Move(Target(Type.BOT, 0), 3), moves[6])

    assertEquals(Comparison(0, 3, 5), comparisons[2])
    assertEquals(Move(Target(Type.OUTPUT, 2), 3), moves[7])
    assertEquals(Move(Target(Type.OUTPUT, 0), 5), moves[8])

    assertEquals(9, moves.size)
    assertEquals(3, comparisons.size)

    val outputs = result.outputs
    assertEquals(5, outputs[0])
    assertEquals(2, outputs[1])
    assertEquals(3, outputs[2])
  }

  @Test
  fun part1() {
    val input = Resources.toString(Resources.getResource("day10.txt"), Charsets.UTF_8)
    val result = Day10.execute(input)
    assertEquals(Comparison(116, 17, 61),
        result.comparisons.filter { it.low == 17 && it.high == 61 }.first())
  }

  @Test
  fun part2() {
    val input = Resources.toString(Resources.getResource("day10.txt"), Charsets.UTF_8)
    val result = Day10.execute(input)
    val outputs = result.outputs
    val output0 = outputs[0] ?: throw IllegalStateException("Should have value in output[0]")
    val output1 = outputs[1] ?: throw IllegalStateException("Should have value in output[1]")
    val output2 = outputs[2] ?: throw IllegalStateException("Should have value in output[2]")
    assertEquals(23903, output0 * output1 * output2)
  }
}