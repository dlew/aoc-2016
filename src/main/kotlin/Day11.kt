import java.util.*

class Day11 {

  enum class Type {
    GENERATOR,
    MICROCHIP
  }

  enum class Element {
    HYDROGEN,
    LITHIUM,
    STRONTIUM,
    PLUTONIUM,
    THULIUM,
    RUTHENIUM,
    CURIUM,
    ELERIUM,
    DILITHIUM
  }

  data class Item(val type: Type, val element: Element) {
    override fun toString() = "${element.name[0]}${type.name[0]}"
  }

  data class State(val floors: List<Set<Item>>, val elevator: Int) {

    fun withMove(item: Item, from: Int, to: Int): State {
      val mutableFloors = floors.toMutableList()
      mutableFloors[from] = mutableFloors[from] - item
      mutableFloors[to] = mutableFloors[to] + item
      return State(mutableFloors, elevator)
    }

    fun withMoves(items: Collection<Item>, from: Int, to: Int): State {
      val mutableFloors = floors.toMutableList()
      mutableFloors[from] = mutableFloors[from] - items
      mutableFloors[to] = mutableFloors[to] + items
      return State(mutableFloors, elevator)
    }

    fun withElevator(floor: Int) = State(floors, floor)

    fun isSolved() = floors.take(floors.size - 1).filter { it.isNotEmpty() }.isEmpty()

    fun validFloor(floor: Int) = validFloor(floors[floor])

    private fun validFloor(floor: Set<Item>): Boolean {
      // No generators == valid floor, guaranteed
      if (floor.filter { it.type == Type.GENERATOR }.isEmpty()) {
        return true
      }

      // Every item is either a generator OR each microchip has its generator present
      return floor.fold(true, { valid, item ->
        valid && (item.type == Type.GENERATOR || floor.contains(Item(Type.GENERATOR, item.element)))
      })
    }

    override fun toString() = floors
        .mapIndexed { num, floor ->
          val sb = StringBuilder("F${num + 1} ")
          sb.append(if (elevator == num) "E  " else "   ")
          floor.forEach {
            sb.append(it.toString() + " ")
          }
          sb.toString()
        }
        .reversed()
        .joinToString("\n")
  }

  companion object {
    fun solve(initialState: State): Int {
      // Keep track of each state we've visited (so we don't visit a state twice)
      val visited = HashSet<State>()
      visited += initialState

      // Keep track of which states we can reach in X # of steps
      var statesAtLastStep = listOf(initialState)

      var step = 1
      while (true) {
        println("${step}: Visited ${visited.size}, expanding ${statesAtLastStep.size}")
        val start = System.currentTimeMillis()

        // Generate all possible next steps (that are valid and haven't already been investigated)
        val possibilities = statesAtLastStep
            .flatMap { generatePossibilities(it) }
            .toSet()
            .filter { !visited.contains(it) }

        // Check if we found a solution
        possibilities.filter { it.isSolved() }.firstOrNull()?.let {
          return step
        }

        // Keep track of all the states we've visited already
        visited.addAll(possibilities)

        // Update state for next cycle
        statesAtLastStep = possibilities
        step++

        // Metrics
        val end = System.currentTimeMillis()
        println("Time taken: ${end - start}ms")
      }
    }

    private fun generatePossibilities(state: State): List<State> {
      val elevator = state.elevator
      val moveOne = state.floors[elevator]
      val moveTwo = combine2(state.floors[state.elevator])

      val possibilities = ArrayList<State>((moveOne.size + moveTwo.size) * 2)

      // Moving items up
      if (elevator < state.floors.size - 1) {
        val newState = state.withElevator(elevator + 1)

        // Move one up
        possibilities += moveOne.map { item -> newState.withMove(item, elevator, elevator + 1) }
            .filter { it.validFloor(elevator) && it.validFloor(elevator + 1) }

        // Move two up (if there are two)
        possibilities += moveTwo.map { items -> newState.withMoves(items, elevator, elevator + 1) }
            .filter { it.validFloor(elevator) && it.validFloor(elevator + 1) }
      }

      // Moving items down
      if (elevator > 0) {
        val newState = state.withElevator(elevator - 1)

        // Move one down
        possibilities += moveOne.map { item -> newState.withMove(item, elevator, elevator - 1) }
            .filter { it.validFloor(elevator) && it.validFloor(elevator - 1) }

        // Move two down (if there are two)
        possibilities += moveTwo.map { items -> newState.withMoves(items, elevator, elevator - 1) }
            .filter { it.validFloor(elevator) && it.validFloor(elevator - 1) }
      }

      return possibilities
    }

    // Optimized combination algorithm that finds all 2-combinations of a set
    fun <T> combine2(items: Set<T>): List<Set<T>> {
      val size = items.size
      if (size < 2) {
        return emptyList()
      }
      if (size == 2) {
        return listOf(items)
      }

      val itemsList = items.toList()
      val combinations = ArrayList<Set<T>>()
      (0..size - 2).forEach { index ->
        (index + 1..size - 1).forEach { index2 ->
          combinations += setOf(itemsList[index], itemsList[index2])
        }
      }
      return combinations
    }
  }
}
