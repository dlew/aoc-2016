class Day16 {

  companion object {
    fun generate(a: String): String {
      val b = a.reversed().map {
        when (it) {
          '0' -> '1'
          '1' -> '0'
          else -> throw IllegalArgumentException("invalid char: $it")
        }
      }.joinToString("")

      return "${a}0${b}"
    }

    fun checksum(text: String): String {
      val result = text.toList().chunk(2)
          .map { if (it[0] == it[1]) '1' else '0' }
          .joinToString("")

      return if (result.length % 2 == 0) checksum(result) else result
    }

    fun scramble(data: String, length: Int): String {
      if (data.length >= length) {
        return checksum(data.take(length))
      }
      else {
        return scramble(generate(data), length)
      }
    }
  }

}