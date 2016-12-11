import Day11.Element.*
import Day11.Item
import Day11.Type.GENERATOR
import Day11.Type.MICROCHIP
import org.junit.Assert.assertEquals
import org.junit.Test

class Day11Test {

  @Test
  fun sample() {
    val setup = listOf(
        setOf(Item(MICROCHIP, HYDROGEN), Item(MICROCHIP, LITHIUM)),
        setOf(Item(GENERATOR, HYDROGEN)),
        setOf(Item(GENERATOR, LITHIUM)),
        emptySet()
    )

    val initialState = Day11.State(setup, 0)
    assertEquals(11, Day11.solve(initialState))
  }

  @Test
  fun part1() {
    val setup = listOf(
        setOf(Item(GENERATOR, STRONTIUM), Item(MICROCHIP, STRONTIUM),
            Item(GENERATOR, PLUTONIUM), Item(MICROCHIP, PLUTONIUM)),
        setOf(Item(GENERATOR, THULIUM), Item(GENERATOR, RUTHENIUM), Item(MICROCHIP, RUTHENIUM),
            Item(GENERATOR, CURIUM), Item(MICROCHIP, CURIUM)),
        setOf(Item(MICROCHIP, THULIUM)),
        emptySet()
    )

    val initialState = Day11.State(setup, 0)
    assertEquals(37, Day11.solve(initialState))
  }

  // Warning, this test takes forever AND you will need to bump up your heap space
  @Test
  fun part2() {
    val setup = listOf(
        setOf(Item(GENERATOR, STRONTIUM), Item(MICROCHIP, STRONTIUM),
            Item(GENERATOR, PLUTONIUM), Item(MICROCHIP, PLUTONIUM),
            Item(GENERATOR, ELERIUM), Item(MICROCHIP, ELERIUM),
            Item(GENERATOR, DILITHIUM), Item(MICROCHIP, DILITHIUM)),
        setOf(Item(GENERATOR, THULIUM), Item(GENERATOR, RUTHENIUM), Item(MICROCHIP, RUTHENIUM),
            Item(GENERATOR, CURIUM), Item(MICROCHIP, CURIUM)),
        setOf(Item(MICROCHIP, THULIUM)),
        emptySet()
    )

    val initialState = Day11.State(setup, 0)
    assertEquals(61, Day11.solve(initialState))
  }
}