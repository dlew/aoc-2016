import java.util.*

class Day25 {

  sealed class Instruction {
    class CopyNum(val num: Int, val to: Char) : Instruction()

    class CopyRegister(val from: Char, val to: Char) : Instruction()

    class CopyInvalid(val num: Int, val invalid: Int) : Instruction()

    class CopyInvalid2(val invalid1: Char, val invalid2: Int) : Instruction()

    class Increment(val register: Char) : Instruction()

    class Decrement(val register: Char) : Instruction()

    class Jump1(val num: Int, val distance: Int) : Instruction()

    class Jump2(val num: Int, val distanceRegister: Char) : Instruction()

    class Jump3(val numRegister: Char, val distance: Int) : Instruction()

    class Jump4(val numRegister: Char, val distanceRegister: Char) : Instruction()

    class Toggle(val register: Char) : Instruction()

    class Transmit(val register: Char) : Instruction()
  }

  data class State(val instructions: List<Instruction>, val pointer: Int)

  companion object {

    private fun isRegister(char: Char) = char >= 'a' && char <= 'd'

    fun parseInstruction(input: String): Instruction {
      val split = input.split(' ')

      return when (input.take(3)) {
        "cpy" -> {
          if (isRegister(split[1][0])) {
            Instruction.CopyRegister(split[1][0], split[2][0])
          } else {
            Instruction.CopyNum(split[1].toInt(), split[2][0])
          }
        }
        "inc" -> Instruction.Increment(split[1][0])
        "dec" -> Instruction.Decrement(split[1][0])
        "jnz" -> {
          val firstRegister = isRegister(split[1][0])
          val secondRegister = isRegister(split[2][0])
          if (!firstRegister && !secondRegister) {
            Instruction.Jump1(split[1].toInt(), split[2].toInt())
          } else if (firstRegister) {
            Instruction.Jump3(split[1][0], split[2].toInt())
          } else if (secondRegister) {
            Instruction.Jump2(split[1].toInt(), split[2][0])
          } else {
            Instruction.Jump4(split[1][0], split[2][0])
          }
        }
        "tgl" -> Instruction.Toggle(split[1][0])
        "out" -> Instruction.Transmit(split[1][0])
        else -> throw IllegalArgumentException("Cannot parse $input")
      }
    }

    fun lowestForClock(input: String): Int {
      val instructions = input.split("\n").map { parseInstruction(it) }

      return generateSequence(1) { it + 1 }
          .first { solve(instructions, hashMapOf('a' to it, 'b' to 0, 'c' to 0, 'd' to 0)) }
    }

    fun solve(input: List<Instruction>, start: Map<Char, Int>): Boolean {
      val registers = HashMap<Char, Int>(start)
      var instructions = input
      var pointer = 0

      var lastOut = 1
      var transmissions = 0

      while (pointer < instructions.size) {
        val instruction = instructions[pointer]

        when (instruction) {
          is Instruction.CopyNum -> {
            registers[instruction.to] = instruction.num
            pointer++
          }
          is Instruction.CopyRegister -> {
            registers[instruction.to] = registers[instruction.from]
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.from}")
            pointer++
          }
          is Instruction.Increment -> {
            val num = registers[instruction.register]
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.register}")
            registers[instruction.register] = num + 1
            pointer++
          }
          is Instruction.Decrement -> {
            registers[instruction.register] = registers[instruction.register]?.minus(1)
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.register}")
            pointer++
          }
          is Instruction.Jump1 -> {
            pointer += jump(instruction.num, instruction.distance)
          }
          is Instruction.Jump2 -> {
            val distance = registers[instruction.distanceRegister]
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.distanceRegister}")
            pointer += jump(instruction.num, distance)
          }
          is Instruction.Jump3 -> {
            val num = registers[instruction.numRegister]
                ?: throw IllegalArgumentException("invalid numRegister: " + "${instruction.numRegister}")
            pointer += jump(num, instruction.distance)
          }
          is Instruction.Jump4 -> {
            val distance = registers[instruction.distanceRegister]
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.distanceRegister}")
            val num = registers[instruction.numRegister]
                ?: throw IllegalArgumentException("invalid numRegister: " + "${instruction.numRegister}")
            pointer += jump(num, distance)
          }
          is Instruction.Toggle -> {
            val distance = registers[instruction.register]
                ?: throw IllegalArgumentException("invalid numRegister: " + "${instruction.register}")
            instructions = toggle(instructions, pointer + distance)

            pointer++
          }
          is Instruction.Transmit -> {
            val num = registers[instruction.register]
                ?: throw IllegalArgumentException("invalid numRegister: " + "${instruction.register}")

            val expectedOut = if (lastOut == 1) 0 else 1
            if (num != expectedOut) {
              return false
            }

            transmissions++
            lastOut = expectedOut

            // Instead of actually doing cycle detection, this is good enough!
            if (transmissions == 10000) {
              return true
            }

            pointer++
          }
          else -> pointer++
        }
      }

      return false
    }

    fun jump(num: Int, distance: Int) = if (num == 0) 1 else distance

    fun toggle(instructions: List<Instruction>, index: Int): List<Instruction> {
      if (index >= instructions.size) {
        return instructions
      }

      val mutableInstructions = instructions.toMutableList()
      val originalInstruction = mutableInstructions.removeAt(index)
      val toggledInstruction = toggle(originalInstruction)
      mutableInstructions.add(index, toggledInstruction)
      return mutableInstructions
    }

    fun toggle(instruction: Instruction) =
        when (instruction) {
          is Instruction.CopyNum -> Instruction.Jump2(instruction.num, instruction.to)
          is Instruction.CopyRegister -> Instruction.Jump4(instruction.from, instruction.to)
          is Instruction.CopyInvalid -> Instruction.Jump1(instruction.num, instruction.invalid)
          is Instruction.CopyInvalid2 -> Instruction.Jump3(instruction.invalid1, instruction.invalid2)
          is Instruction.Increment -> Instruction.Decrement(instruction.register)
          is Instruction.Decrement -> Instruction.Increment(instruction.register)
          is Instruction.Jump1 -> Instruction.CopyInvalid(instruction.num, instruction.distance)
          is Instruction.Jump2 -> Instruction.CopyNum(instruction.num, instruction.distanceRegister)
          is Instruction.Jump3 -> Instruction.CopyInvalid2(instruction.numRegister, instruction.distance)
          is Instruction.Jump4 -> Instruction.CopyRegister(instruction.numRegister, instruction.distanceRegister)
          is Instruction.Toggle -> Instruction.Increment(instruction.register)
          is Instruction.Transmit -> Instruction.Increment(instruction.register)
        }
  }

}