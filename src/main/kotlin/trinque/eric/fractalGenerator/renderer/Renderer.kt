import javafx.scene.Node
import ContentNode

interface ContentRenderer {
    fun render(node: ContentNode): Node
}