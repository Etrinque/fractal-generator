sealed interface ContentNode {}

data class JSONNode(val json: Map<String, Unit>) : ContentNode {}
data class TextNode(val text: String) : ContentNode {}
data class ImageNode(val title: String = "", val altText: String? = null, val image: ByteArray? = null) :
    ContentNode {}

data class ContainerNode(val children: List<ContentNode>) :
    ContentNode {}
