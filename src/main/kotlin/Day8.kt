class Day8 {

  data class Rect(val width: Int, val height: Int)

  data class RotateRow(val row: Int, val pixels: Int)

  data class RotateColumn(val col: Int, val pixels: Int)

  data class Screen(val grid: Array<Array<Boolean>>)

  companion object {
    fun createScreen(width: Int, height: Int) = Screen(Array(height, { Array(width, { false }) }))

    fun printScreen(screen: Screen): String {
      val sb = StringBuilder()
      screen.grid.forEach { row ->
        sb.appendln(row.map { if (it) '#' else '.' }.joinToString(""))
      }
      return sb.toString()
    }

    fun copyScreen(screen: Screen) = Screen(Array(screen.grid.size, { i -> screen.grid[i].clone() }))

    fun countLit(screen: Screen) = screen.grid.fold(0, { count, row ->
      count + row.fold(0, { subCount, item -> if (item) subCount + 1 else subCount })
    })

    fun applyCommand(screen: Screen, command: Any): Screen {
      return when (command) {
        is Rect -> rect(screen, command.width, command.height)
        is RotateRow -> rotateRow(screen, command.row, command.pixels)
        is RotateColumn -> rotateCol(screen, command.col, command.pixels)
        else -> throw IllegalArgumentException("Illegal command: $command")
      }
    }

    fun parseCommand(input: String): Any {
      if (input.startsWith("rect")) {
        val split = input.drop("rect ".length).split('x')
        return Rect(split[0].toInt(), split[1].toInt())
      } else if (input.startsWith("rotate row")) {
        val split = input.drop("rotate row y=".length).split(" by ")
        return RotateRow(split[0].toInt(), split[1].toInt())
      } else if (input.startsWith("rotate column")) {
        val split = input.drop("rotate column x=".length).split(" by ")
        return RotateColumn(split[0].toInt(), split[1].toInt())
      } else {
        throw IllegalArgumentException("Invalid command: $input")
      }
    }

    private fun rect(screen: Screen, width: Int, height: Int): Screen {
      val newScreen = copyScreen(screen)
      (0..height - 1).forEach { row ->
        (0..width - 1).forEach { col ->
          newScreen.grid[row][col] = true
        }
      }
      return newScreen
    }

    private fun rotateRow(screen: Screen, row: Int, pixels: Int): Screen {
      val newScreen = copyScreen(screen)
      val width = screen.grid[0].size

      (0..width - 1).forEach { col ->
        val newCol = (col + pixels) % width
        newScreen.grid[row][newCol] = screen.grid[row][col]
      }

      return newScreen
    }

    private fun rotateCol(screen: Screen, col: Int, pixels: Int): Screen {
      val newScreen = copyScreen(screen)
      val height = screen.grid.size

      (0..height - 1).forEach { row ->
        val newRow = (row + pixels) % height
        newScreen.grid[newRow][col] = screen.grid[row][col]
      }

      return newScreen
    }
  }

}