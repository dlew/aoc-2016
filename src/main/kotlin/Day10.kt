import java.util.*
import java.util.regex.Pattern

class Day10 {
  enum class Type {
    BOT,
    OUTPUT
  }

  data class Target(val type: Type, val num: Int)

  data class Instruction(val low: Target, val high: Target)

  data class Move(val target: Target, val value: Int)

  data class Setup(val instructions: Map<Int, Instruction>, val input: List<Move>)

  data class Comparison(val botNum: Int, val low: Int, val high: Int)

  data class Result(val moves: List<Move>, val comparisons: List<Comparison>, val outputs: Map<Int, Int>)

  companion object {
    private val INPUT_PATTERN = Pattern.compile("value (\\d+) goes to bot (\\d+)")

    private val INSTRUCTION_PATTERN =
        Pattern.compile("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)")

    fun execute(input: String) = executeSetup(parseSetup(input))

    private fun parseSetup(text: String): Setup {
      val instructions = HashMap<Int, Instruction>()
      val inputs = ArrayList<Move>()

      text.split('\n').forEach { line ->
        val inputMatcher = INPUT_PATTERN.matcher(line)
        val instructionMatcher = INSTRUCTION_PATTERN.matcher(line)
        if (inputMatcher.matches()) {
          val target = Target(Type.BOT, inputMatcher.group(2).toInt())
          inputs.add(Move(target, inputMatcher.group(1).toInt()))
        } else if (instructionMatcher.matches()) {
          val bot = instructionMatcher.group(1).toInt()
          val lowTarget = parseTarget(instructionMatcher.group(2))
          val lowNum = instructionMatcher.group(3).toInt()
          val highTarget = parseTarget(instructionMatcher.group(4))
          val highNum = instructionMatcher.group(5).toInt()
          instructions.put(bot, Instruction(Target(lowTarget, lowNum), Target(highTarget, highNum)))
        } else {
          throw IllegalArgumentException("Cannot parse line: $line")
        }
      }

      return Setup(instructions, inputs)
    }

    private fun parseTarget(text: String) =
        when (text) {
          "bot" -> Type.BOT
          "output" -> Type.OUTPUT
          else -> throw IllegalArgumentException("Unknown target: $text")
        }

    private fun executeSetup(setup: Setup): Result {
      // Represents what each bot is holding
      val bots = HashMap<Int, Int>()

      // Represents what has gone into each output
      val outputs = HashMap<Int, Int>()

      // Queue of moves to make
      val queue = ArrayList<Move>(setup.input)

      // All input that were made during input
      val moves = ArrayList<Move>()
      val comparisons = ArrayList<Comparison>()

      while (queue.isNotEmpty()) {
        val input = queue.removeAt(0)
        moves.add(input)

        val num = input.target.num
        when (input.target.type) {
          Type.OUTPUT -> {
            outputs[num] = input.value
          }
          Type.BOT -> {
            val currVal = bots.remove(num)

            if (currVal == null) {
              bots[num] = input.value
            } else {
              val instruction = setup.instructions[num] ?:
                  throw IllegalStateException("Tried to get instructions for bot $num but there were none!")

              val low: Int
              val high: Int
              if (currVal < input.value) {
                low = currVal
                high = input.value
              } else {
                low = input.value
                high = currVal
              }

              queue.add(Move(instruction.low, low))
              queue.add(Move(instruction.high, high))

              comparisons.add(Comparison(num, low, high))
            }
          }
        }
      }

      return Result(moves, comparisons, outputs)
    }
  }
}