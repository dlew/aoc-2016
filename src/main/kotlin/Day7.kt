import java.util.regex.Pattern

class Day7 {
  data class Address(val sequences: List<String>, val hypernet: List<String>) {
    fun supportsTLS() = containsAbba(sequences) && !containsAbba(hypernet)

    fun supportsSSL(): Boolean {
      val sequenceAbas = sequences.map { findAbas(it) }.flatten()
      val hypernetAbas = hypernet.map { findAbas(it) }.flatten().map { reverseAba(it) }
      return sequenceAbas.intersect(hypernetAbas).size != 0
    }
  }

  companion object {
    private val BRACKETS = Pattern.compile("[\\[\\]]")

    fun address(input: String) = input.split(BRACKETS)
        .foldIndexed(Address(emptyList(), emptyList()), { index, address, sequence ->
          if (index % 2 == 0) Address(address.sequences.plus(sequence), address.hypernet)
          else Address(address.sequences, address.hypernet.plus(sequence))
        })

    fun findAbas(input: String) = input.toList()
        .window(3)
        .fold(emptyList<String>(), { abas, text -> if (isAba(text)) abas.plusElement(text.joinToString("")) else abas })

    fun reverseAba(input: String) = "${input[1]}${input[0]}${input[1]}"

    fun containsAbba(input: List<String>) = input
        .fold(false, { hasAbba, text -> if (hasAbba) true else containsAbba(text) })

    fun containsAbba(input: String) = input.toList()
        .window(4)
        .fold(false, { hasAbba, text -> if (hasAbba) true else isAbba(text) })

    private fun isAbba(input: List<Char>) = input[0] == input[3] && input[1] == input[2] && input[0] != input[1]

    private fun isAba(input: List<Char>) = input[0] == input[2] && input[0] != input[1]
  }

}