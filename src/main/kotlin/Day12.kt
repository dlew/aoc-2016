import java.util.*

class Day12 {

  sealed class Instruction {
    class CopyNum(val num: Int, val to: Char) : Instruction()

    class CopyRegister(val from: Char, val to: Char) : Instruction()

    class Increment(val register: Char) : Instruction()

    class Decrement(val register: Char) : Instruction()

    class JumpNum(val num: Int, val distance: Int) : Instruction()

    class JumpRegister(val register: Char, val distance: Int) : Instruction()
  }

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
          if (isRegister(split[1][0])) {
            Instruction.JumpRegister(split[1][0], split[2].toInt())
          } else {
            Instruction.JumpNum(split[1].toInt(), split[2].toInt())
          }
        }
        else -> throw IllegalArgumentException("Cannot parse $input")
      }
    }

    fun solve(input: String) = solve(input, hashMapOf('a' to 0, 'b' to 0, 'c' to 0, 'd' to 0))

    fun solve2(input: String) = solve(input, hashMapOf('a' to 0, 'b' to 0, 'c' to 1, 'd' to 0))

    fun solve(input: String, start: Map<Char, Int>): HashMap<Char, Int> {
      val registers = HashMap<Char, Int>(start)
      val instructions = input.split("\n").map { parseInstruction(it) }
      var pointer = 0

      while (pointer < instructions.size) {
        val instruction = instructions[pointer]

        when (instruction) {
          is Instruction.CopyNum -> {
            registers[instruction.to] = instruction.num
            pointer++
          }
          is Instruction.CopyRegister -> {
            registers[instruction.to] = registers[instruction.from]
                ?: throw IllegalArgumentException("invalid register: ${instruction.from}")
            pointer++
          }
          is Instruction.Increment -> {
            registers[instruction.register] = registers[instruction.register]?.plus(1)
                ?: throw IllegalArgumentException("invalid register: ${instruction.register}")
            pointer++
          }
          is Instruction.Decrement -> {
            registers[instruction.register] = registers[instruction.register]?.minus(1)
                ?: throw IllegalArgumentException("invalid register: ${instruction.register}")
            pointer++
          }
          is Instruction.JumpNum -> {
            if (instruction.num != 0) {
              pointer += instruction.distance
            }
            else {
              pointer++
            }
          }
          is Instruction.JumpRegister -> {
            if (registers[instruction.register] != 0) {
              pointer += instruction.distance
            }
            else {
              pointer++
            }
          }
        }
      }

      return registers
    }
  }
}