import java.util.*
import java.util.regex.Pattern

class Day4 {

  data class Count(val char: Char, val count: Int)

  data class Room(val name: String, val id: Int, val checksum: String) {
    fun calculateChecksum() = name.fold(HashMap<Char, Int>(), { charCount, char ->
      charCount.put(char, charCount.getOrDefault(char, 0) + 1)
      charCount
    })
        .map { Count(it.key, it.value) }
        .groupBy { it.count }
        .map { Pair(it.key, it.value) }
        .sortedByDescending { it.first }
        .flatMap { it.second.sortedBy { it.char } }
        .take(5)
        .map { it.char }
        .fold("", { checksum, char -> checksum + char })

    fun isReal(): Boolean {
      return calculateChecksum() == this.checksum
    }
  }

  companion object {
    private val ROOM_MATCHER = Pattern.compile("([a-z-]+)-(\\d+)\\[([a-z]{5})\\]")

    fun parseRoom(room: String) = ROOM_MATCHER.matcher(room).run {
      matches() // Need to run this, otherwise the Matcher does nothing!
      Room(group(1).replace("-", ""), group(2).toInt(), group(3))
    }

    fun addRooms(rooms: List<Room>) = rooms.fold(0, { sum, room -> sum + room.id })
  }
}