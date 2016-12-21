import java.util.regex.Pattern

class Day21 {
  companion object {
    private val SWAP_POSITION_PATTERN = Pattern.compile("swap position (\\d+) with position (\\d+)")
    private val SWAP_LETTER_PATTERN = Pattern.compile("swap letter (\\w) with letter (\\w)")
    private val ROTATE_PATTERN = Pattern.compile("rotate (left|right) (\\d+) steps?")
    private val ROTATE_PIVOT_PATTERN = Pattern.compile("rotate based on position of letter (\\w)")
    private val REVERSE_PATTERN = Pattern.compile("reverse positions (\\d+) through (\\d+)")
    private val MOVE_PATTERN = Pattern.compile("move position (\\d+) to position (\\d+)")

    fun apply(password: String, input: String): String {
      if (input.startsWith("swap position")) {
        val matcher = SWAP_POSITION_PATTERN.matcher(input)
        matcher.matches()

        val x = matcher.group(1).toInt()
        val y = matcher.group(2).toInt()

        return password.swap(x, y)
      } else if (input.startsWith("swap letter")) {
        val matcher = SWAP_LETTER_PATTERN.matcher(input)
        matcher.matches()

        val x = matcher.group(1)[0]
        val y = matcher.group(2)[0]

        val indexOfX = password.indexOf(x)
        val indexOfY = password.indexOf(y)
        return password.swap(indexOfX, indexOfY)
      } else if (input.startsWith("rotate left")) {
        val matcher = ROTATE_PATTERN.matcher(input)
        matcher.matches()

        val shift = matcher.group(2).toInt()

        return password.rotateLeft(shift)
      } else if (input.startsWith("rotate right")) {
        val matcher = ROTATE_PATTERN.matcher(input)
        matcher.matches()

        val shift = matcher.group(2).toInt()

        return password.rotateRight(shift)
      } else if (input.startsWith("rotate based on position")) {
        val matcher = ROTATE_PIVOT_PATTERN.matcher(input)
        matcher.matches()

        val char = matcher.group(1)[0]

        return password.rotateBy(char)
      } else if (input.startsWith("reverse positions")) {
        val matcher = REVERSE_PATTERN.matcher(input)
        matcher.matches()

        val x = matcher.group(1).toInt()
        val y = matcher.group(2).toInt()

        return password.reversePart(x, y)
      } else if (input.startsWith("move position")) {
        val matcher = MOVE_PATTERN.matcher(input)
        matcher.matches()

        val x = matcher.group(1).toInt()
        val y = matcher.group(2).toInt()

        return password.move(x, y)
      }

      throw IllegalArgumentException("Could not parse: $input")
    }

    fun undo(password: String, input: String): String {
      if (input.startsWith("swap position")) {
        val matcher = SWAP_POSITION_PATTERN.matcher(input)
        matcher.matches()

        val x = matcher.group(1).toInt()
        val y = matcher.group(2).toInt()

        return password.swap(x, y)
      } else if (input.startsWith("swap letter")) {
        val matcher = SWAP_LETTER_PATTERN.matcher(input)
        matcher.matches()

        val x = matcher.group(1)[0]
        val y = matcher.group(2)[0]

        val indexOfX = password.indexOf(x)
        val indexOfY = password.indexOf(y)
        return password.swap(indexOfX, indexOfY)
      } else if (input.startsWith("rotate left")) {
        val matcher = ROTATE_PATTERN.matcher(input)
        matcher.matches()

        val shift = matcher.group(2).toInt()

        return password.rotateRight(shift)
      } else if (input.startsWith("rotate right")) {
        val matcher = ROTATE_PATTERN.matcher(input)
        matcher.matches()

        val shift = matcher.group(2).toInt()

        return password.rotateLeft(shift)
      } else if (input.startsWith("rotate based on position")) {
        val matcher = ROTATE_PIVOT_PATTERN.matcher(input)
        matcher.matches()

        val char = matcher.group(1)[0]

        var rotatedPassword = password
        while (true) {
          val rotated = rotatedPassword.rotateBy(char)
          if (rotated == password) {
            return rotatedPassword
          }
          rotatedPassword = rotatedPassword.rotateLeft(1)
        }
      } else if (input.startsWith("reverse positions")) {
        val matcher = REVERSE_PATTERN.matcher(input)
        matcher.matches()
        val x = matcher.group(1).toInt()
        val y = matcher.group(2).toInt()

        return password.reversePart(x, y)
      } else if (input.startsWith("move position")) {
        val matcher = MOVE_PATTERN.matcher(input)
        matcher.matches()
        val x = matcher.group(1).toInt()
        val y = matcher.group(2).toInt()

        return password.move(y, x)
      }

      throw IllegalArgumentException("Could not parse: $input")
    }

    private fun String.swap(x: Int, y: Int): String {
      val list = toMutableList()
      val tmp = list[x]
      list[x] = list[y]
      list[y] = tmp
      return list.joinToString("")
    }

    private fun String.rotateLeft(shift: Int): String {
      val charList = toMutableList()
      (0..length - 1).forEach { pos ->
        val newPos = (pos - shift + length) % length
        charList[newPos] = this[pos]
      }
      return charList.joinToString("")
    }

    private fun String.rotateRight(shift: Int): String {
      val charList = toMutableList()
      (0..length - 1).forEach { pos ->
        val newPos = (pos + shift) % length
        charList[newPos] = this[pos]
      }
      return charList.joinToString("")
    }

    private fun String.rotateBy(char: Char): String {
      val index = indexOf(char)
      val shift = 1 + index + (if (index >= 4) 1 else 0)
      return rotateRight(shift)
    }

    private fun String.reversePart(x: Int, y: Int): String {
      return take(x) + slice((x..y)).reversed() + drop(y + 1)
    }

    private fun String.move(x: Int, y: Int): String {
      val charList = toMutableList()
      val char = charList.removeAt(x)
      charList.add(y, char)
      return charList.joinToString("")
    }
  }
}