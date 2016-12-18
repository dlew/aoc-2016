class Day18 {
  companion object {
    fun parseRow(row: String) = row.map {
      when (it) {
        '.' -> false
        '^' -> true
        else -> throw IllegalArgumentException("Cannot handle $it")
      }
    }

    fun isTrap(left: Boolean, center: Boolean, right: Boolean): Boolean {
      return (left && center && !right)
          || (center && right && !left)
          || (left && !center && !right)
          || (!left && !center && right)
    }

    fun nextRow(row: List<Boolean>) =
        (listOf(false) + row + listOf(false))
            .slidingWindow(3)
            .map { isTrap(it[0], it[1], it[2]) }

    fun countSafeTiles(row: List<Boolean>, rows: Int): Int {
      var curr = row
      var count = 0
      (0..rows - 1)
          .forEach {
            count += curr.count { !it }
            curr = nextRow(curr)
          }
      return count
    }
  }
}