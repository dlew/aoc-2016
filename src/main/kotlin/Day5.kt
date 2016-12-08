import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils

class Day5 {

  data class PassChar(val char: Char, val pos: Int)

  companion object {
    fun md5(text: String) = Hex.encodeHex(DigestUtils.md5(text)).joinToString("")

    fun passwordChar(hash: String) = if (hash.startsWith("00000")) hash[5] else null

    fun passwordCharHard(hash: String) = if (hash.startsWith("00000")) PassChar(hash[6], hash[5].toInt() - 48) else null

    fun nextPasswordIndex(id: String, start: Int) =
        generateSequence(start) { it + 1 }
            .first { passwordChar(md5("$id$it")) != null }

    fun findPassword(id: String, len: Int): String {
      return generateSequence(0) { it + 1 }
          .map { passwordChar(md5("$id$it")) }
          .filterNotNull()
          .take(len)
          .joinToString("")
    }

    fun findPasswordHard(id: String, len: Int): String {
      return generateSequence(0) { it + 1 }
          .map { passwordCharHard(md5("$id$it")) }
          .filterNotNull()
          .scan(Array<Char?>(len, { null }), { arr, passChar ->
            if (passChar.pos < len && arr[passChar.pos] == null) {
              val newArr = arr.clone()
              newArr[passChar.pos] = passChar.char
              newArr
            }
            else {
              arr
            }
          })
          .first { !it.contains(null) }
          .joinToString("")
    }
  }

}