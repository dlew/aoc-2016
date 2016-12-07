import java.util.regex.Pattern

class Day3 {

  companion object {
    private val WHITESPACE = Pattern.compile("\\s+")

    private fun parseInput(input: String): List<List<Int>> =
        input.split("\n").map { line ->
          line.trim().split(WHITESPACE).map { it.toInt() }
        }

    fun numValidTriangles(triangles: List<List<Int>>) =
        triangles.fold(0, { numValid, sides -> numValid + if (isValidTriangle(sides)) 1 else 0 })

    fun numValidTriangles(input: String) = numValidTriangles(parseInput(input))

    fun numValidTrianglesTranspose(input: String) = numValidTriangles(
        parseInput(input)
            .chunk(3)
            .map { it.transpose() }
            .flatten()
    )

    fun isValidTriangle(sides: List<Int>) =
        sides[0] + sides[1] > sides[2]
            && sides[1] + sides[2] > sides[0]
            && sides[0] + sides[2] > sides[1]
  }

}