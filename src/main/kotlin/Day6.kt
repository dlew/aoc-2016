class Day6 {

  companion object {
    fun decrypt(input: String, mostCommon: Boolean = true) = input.split('\n')
        .map { it.toList() }
        .transpose()
        .map { if (mostCommon) findMostCommonLetter(it) else findLeastCommonLetter(it) }
        .joinToString("")

    fun findMostCommonLetter(list: List<Char>) =
        list.charCounts()
            .map { Pair(it.key, it.value) }
            .sortedByDescending { it.second }
            .first().first

    fun findLeastCommonLetter(list: List<Char>) =
        list.charCounts()
            .map { Pair(it.key, it.value) }
            .sortedBy { it.second }
            .first().first
  }

}