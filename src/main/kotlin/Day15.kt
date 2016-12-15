class Day15 {

  data class Disc(val positions: Int, val start: Int) {
    fun positionAtTime(time: Int) = (start + time) % positions
  }

  companion object {
    fun firstWinner(discs: List<Disc>): Int {
      return generateSequence(0) { it + 1 }
          .filter { time ->
            discs.mapIndexed { index, disc -> disc.positionAtTime(index + time + 1) }
                .firstOrNull { it != 0 } == null
          }
          .first()
    }
  }
}