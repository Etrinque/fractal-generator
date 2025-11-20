import trinque.eric.fractalGenerator.content.Content
import trinque.eric.fractalGenerator.content.ContentType

interface ContentParser<T> {
    val Type: ContentType
    fun parse(content: Content): T
}