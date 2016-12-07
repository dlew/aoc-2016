import java.util.*

inline fun <T, R> Iterable<T>.scan(initial: R, operation: (R, T) -> R): Iterable<R> {
  val results = mutableListOf(initial)
  for (element in this) results += operation(results.last(), element)
  return results
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