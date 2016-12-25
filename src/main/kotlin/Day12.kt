import java.util.*

class Day12 {

  sealed class Instruction {
    class CopyNum(val num: Int, val to: Char) : Instruction()

    class CopyRegister(val from: Char, val to: Char) : Instruction()

    class CopyInvalid(val num: Int, val invalid: Int) : Instruction()

    class CopyInvalid2(val invalid1: Char, val invalid2: Int) : Instruction()

    class Increment(val register: Char) : Instruction()

    class Decrement(val register: Char) : Instruction()

    class JumpNum(val num: Int, val distance: Int) : Instruction()

    class JumpRegister(val register: Char, val distance: Int) : Instruction()

    class JumpInvalid(val num: Int, val invalid: Char) : Instruction()

    class JumpInvalid2(val invalid1: Char, val invalid2: Char) : Instruction()

    class Toggle(val register: Char) : Instruction()
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
        "tgl" -> Instruction.Toggle(split[1][0])
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
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.from}")
            pointer++
          }
          is Instruction.Increment -> {
            registers[instruction.register] = registers[instruction.register]?.plus(1)
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.register}")
            pointer++
          }
          is Instruction.Decrement -> {
            registers[instruction.register] = registers[instruction.register]?.minus(1)
                ?: throw IllegalArgumentException("invalid numRegister: ${instruction.register}")
            pointer++
          }
          is Instruction.JumpNum -> {
            if (instruction.num != 0) {
              pointer += instruction.distance
            } else {
              pointer++
            }
          }
          is Instruction.JumpRegister -> {
            if (registers[instruction.register] != 0) {
              pointer += instruction.distance
            } else {
              pointer++
            }
          }
        }
      }

      return registers
    }

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
          is Instruction.CopyNum -> Instruction.JumpInvalid(instruction.num, instruction.to)
          is Instruction.CopyRegister -> Instruction.JumpInvalid2(instruction.from, instruction.to)
          is Instruction.CopyInvalid -> Instruction.JumpNum(instruction.num, instruction.invalid)
          is Instruction.CopyInvalid2 -> Instruction.JumpRegister(instruction.invalid1, instruction.invalid2)
          is Instruction.Increment -> Instruction.Decrement(instruction.register)
          is Instruction.Decrement -> Instruction.Increment(instruction.register)
          is Instruction.JumpNum -> Instruction.CopyInvalid(instruction.num, instruction.distance)
          is Instruction.JumpRegister -> Instruction.CopyInvalid2(instruction.register, instruction.distance)
          is Instruction.JumpInvalid -> Instruction.CopyNum(instruction.num, instruction.invalid)
          is Instruction.JumpInvalid2 -> Instruction.CopyRegister(instruction.invalid1, instruction.invalid2)
          is Instruction.Toggle -> Instruction.Increment(instruction.register)
        }
  }
}