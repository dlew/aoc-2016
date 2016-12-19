import java.util.*

class Day19 {

  companion object {
    fun solve(numElves: Int): Int {
      val elves = (0..numElves - 1).toMutableList()

      var currElf = 0
      while (elves.size != 1) {
        val sadElf = increment(currElf, elves.size)
        elves.removeAt(sadElf)
        currElf = sadElf
        if (currElf == elves.size) {
          currElf = 0
        }
      }

      return elves[0] + 1
    }

    fun solve2(numElves: Int): Int {
      val elves = (0..numElves - 1).toMutableList()

      var currElf = 0
      while (elves.size != 1) {
        val sadElf = (currElf + (elves.size / 2)) % elves.size
        elves.removeAt(sadElf)
        currElf = if (sadElf < currElf) currElf else currElf + 1
        if (currElf == elves.size) {
          currElf = 0
        }
      }

      return elves[0] + 1
    }

    private inline fun increment(curr: Int, max: Int) = if (curr == max - 1) 0 else curr + 1
  }
}