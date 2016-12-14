import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.util.*

class Day14 {

  companion object {
    fun md5(text: String) = Hex.encodeHex(DigestUtils.md5(text), true).joinToString("")

    fun generate(salt: String, index: Int, stretch: Int = 1) = (1..stretch)
        .fold(salt + index.toString(), { hash, ignore -> md5(hash) })

    fun indexOfKey(salt: String, num: Int, stretch: Int = 1): Int {
      var foundCount = 0
      val hashes = HashMap<Int, String>()

      // Preload the first 1000 hashes
      (0..999).forEach { index ->
        hashes.put(index, generate(salt, index, stretch))
      }

      generateSequence(0, { it + 1 })
          .forEach { index ->
            // Generate any hashes we might need for this index
            hashes.put(index + 1000, generate(salt, index + 1000, stretch))

            val repeatChar = findRepeat(hashes.getOrElse(index, { throw IllegalStateException("No key!") }), 3)
            if (repeatChar != null) {
              if ((index + 1..index + 1000)
                  .map { subIndex ->
                    findRepeat(hashes.getOrElse(subIndex, { throw IllegalStateException("No key!") }), 5, repeatChar)
                  }
                  .filterNotNull()
                  .isNotEmpty()) {
                foundCount++
                if (foundCount == num) {
                  return index
                }
              }
            }
          }

      throw IllegalStateException("You should never get here!")
    }

    fun findRepeat(text: String, num: Int, target: Char? = null): Char? {
      var lastChar = ' '
      var repeatCount = if (target == null) 1 else 0
      text.forEach { char ->
        if (target == char || char == lastChar) {
          repeatCount++
          if (repeatCount == num) {
            return if (target != null) target else lastChar
          }
        } else {
          if (target == null) {
            lastChar = char
            repeatCount = 1
          } else {
            repeatCount = 0
          }
        }
      }

      return null
    }
  }
}