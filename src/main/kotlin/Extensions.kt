import java.util.*

inline fun <T, R> Iterable<T>.scan(initial: R, operation: (R, T) -> R): Iterable<R> {
  val results = mutableListOf(initial)
  for (element in this) results += operation(results.last(), element)
  return results
}

fun <T, R> Sequence<T>.scan(initial: R, operation: (R, T) -> R): Sequence<R>
    = ScanningSequence(this, initial, operation)

internal class ScanningSequence<T, R>
constructor(private val sequence: Sequence<T>,
            private val initial: R,
            private val operation: (R, T) -> R) : Sequence<R> {

  override fun iterator(): Iterator<R> = object : Iterator<R> {
    val iterator = sequence.iterator()
    var state = initial

    override fun next(): R {
      state = operation(state, iterator.next())
      return state
    }

    override fun hasNext(): Boolean {
      return iterator.hasNext()
    }
  }
}

fun <T> Iterable<Iterable<T>>.transpose(): List<List<T>> {
  val results = ArrayList<MutableList<T>>()
  forEach { list ->
    list.forEachIndexed { i, item ->
      if (results.size <= i) {
        results.add(ArrayList<T>())
      }
      results[i].add(item)
    }
  }
  return results
}

fun <T> Iterable<T>.chunk(size: Int): Iterable<Iterable<T>> {
  val results = ArrayList<MutableList<T>>()
  forEachIndexed { i, item ->
    if (i % size == 0) {
      results.add(ArrayList<T>())
    }
    results.last().add(item)
  }
  return results
}

// Since this has come up multiple times in the puzzles...
fun Iterable<Char>.charCounts(): Map<Char, Int> {
  return fold(HashMap<Char, Int>(), { charCount, char ->
    charCount.put(char, charCount.getOrDefault(char, 0) + 1)
    charCount
  })
}