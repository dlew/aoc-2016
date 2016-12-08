import com.google.common.io.Resources
import org.junit.Assert.*
import org.junit.Test

class Day4Test {
  @Test
  fun sample1() {
    assertTrue(Day4.parseRoom("aaaaa-bbb-z-y-x-123[abxyz]").isReal())
  }

  @Test
  fun sample2() {
    assertTrue(Day4.parseRoom("a-b-c-d-e-f-g-h-987[abcde]").isReal())
  }

  @Test
  fun sample2remix() {
    assertTrue(Day4.parseRoom("f-e-d-c-b-a-g-h-987[abcde]").isReal())
  }

  @Test
  fun sample3() {
    assertTrue(Day4.parseRoom("not-a-real-room-404[oarel]").isReal())
  }

  @Test
  fun sample4() {
    assertFalse(Day4.parseRoom("totally-real-room-200[decoy]").isReal())
  }

  @Test
  fun sample5() {
    val rooms = listOf(
        Day4.parseRoom("aaaaa-bbb-z-y-x-123[abxyz]"),
        Day4.parseRoom("a-b-c-d-e-f-g-h-987[abcde]"),
        Day4.parseRoom("not-a-real-room-404[oarel]")
    )
    assertEquals(1514, Day4.addRooms(rooms))
  }

  @Test
  fun solution() {
    val input = Resources.toString(Resources.getResource("day4.txt"), Charsets.UTF_8)

    val realRooms = input.split('\n')
        .map { Day4.parseRoom(it) }
        .filter { it.isReal() }

    val answer = realRooms.fold(0, { sum, room -> sum + room.id })

    assertEquals(409147, answer)

    realRooms.forEach { println("${it.decrypt()}: ${it.id}") }
  }
}