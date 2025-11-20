package trinque.eric.fractalGenerator.content

import java.nio.charset.Charset
import ContentNode


data class Content(
    val type: ContentType,
    var title: String,
    var raw: ByteArray,
    var root: ContentNode? = null
) {
    fun string(charset: Charset = Charsets.UTF_8): String = raw.toString(charset)
}


