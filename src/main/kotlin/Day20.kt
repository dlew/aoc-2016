import java.util.*

class Day20 {
  companion object {
    fun findLowest(input: String) = merge(parse(input))[0].endInclusive + 1

    fun count(input: String, max: Long): Long {
      val merged = merge(parse(input))
      val betweenRanges = merged.slidingWindow(2).fold(0L, { count, ranges ->
        count + (ranges[1].start - ranges[0].endInclusive - 1)
      })
      val extra = Math.max(max - merged.last().endInclusive, 0)
      return betweenRanges + extra
    }

    private fun parse(input: String) = input.split('\n')
        .map {
          val split = it.split('-')
          LongRange(split[0].toLong(), split[1].toLong())
        }

    private fun merge(ranges: List<LongRange>)
        = ranges.sortedBy { it.start }
        .fold(ArrayList<LongRange>(), { merged, range ->
          if (merged.size != 0 && merged.last().endInclusive + 1 >= range.start) {
            val last = merged.last()
            if (last.endInclusive > range.endInclusive) {
              merged
            } else {
              merged.removeAt(merged.size - 1)
              merged.add(LongRange(last.start, range.endInclusive))
              merged
            }
          } else {
            merged.add(range)
            merged
          }
        })
  }
}