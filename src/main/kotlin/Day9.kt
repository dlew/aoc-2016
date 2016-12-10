import java.util.regex.Pattern

class Day9 {

  companion object {
    private val MARKER_PATTERN = Pattern.compile("\\((\\d+)x(\\d+)\\)")

    // I'd originally done this in a much more composeable manner, but it was running too slowly,
    // so I optimized the daylights out of it.
    //
    // I'm keeping this for posterity, but since the StringBuilder cannot handle the size of the part2
    // solution, I need to simply count characters for part2
    fun decompress(input: String, v2: Boolean): String {
      var decompressed = StringBuilder()
      var compressed = input
      do {
        val matcher = MARKER_PATTERN.matcher(compressed)
        val nextMarkerLocation = if (matcher.find()) (matcher.start()..matcher.end() - 1) else null
        nextMarkerLocation?.let {
          val preMarkerText = compressed.take(nextMarkerLocation.start)
          val restText = compressed.drop(nextMarkerLocation.last + 1)
          val size = matcher.group(1).toInt()
          val times = matcher.group(2).toInt()

          val markerExpansion: String
          val textToRepeat = restText.take(size)
          if (v2) {
            markerExpansion = decompress(textToRepeat, true).repeat(times)
          }
          else {
            markerExpansion = textToRepeat.repeat(times)
          }
          val markerRemainder = restText.drop(size)

          decompressed.append(preMarkerText).append(markerExpansion)
          compressed = markerRemainder
        }
      } while (nextMarkerLocation != null)

      decompressed.append(compressed)

      return decompressed.toString().trim()
    }

    fun decompressLength(input: String, v2: Boolean): Long {
      var decompressedLength: Long = 0
      var compressed = input
      do {
        val matcher = MARKER_PATTERN.matcher(compressed)
        val nextMarkerLocation = if (matcher.find()) (matcher.start()..matcher.end() - 1) else null
        nextMarkerLocation?.let {
          val preMarkerText = compressed.take(nextMarkerLocation.start)
          val restText = compressed.drop(nextMarkerLocation.last + 1)
          val size = matcher.group(1).toInt()
          val times = matcher.group(2).toInt()

          val markerExpansionLength: Long
          val textToRepeat = restText.take(size)
          if (v2) {
            markerExpansionLength = decompressLength(textToRepeat, true) * times
          }
          else {
            markerExpansionLength = (textToRepeat.length * times).toLong()
          }
          val markerRemainder = restText.drop(size)

          decompressedLength += preMarkerText.length + markerExpansionLength
          compressed = markerRemainder
        }
      } while (nextMarkerLocation != null)

      decompressedLength  += compressed.trim().length

      return decompressedLength
    }
  }


}