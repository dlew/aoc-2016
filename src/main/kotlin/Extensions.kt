inline fun <T, R> Iterable<T>.scan(initial: R, operation: (R, T) -> R): List<R> {
  val results: MutableList<R> = mutableListOf(initial)
  for (element in this) results += operation(results.last(), element)
  return results
}