package trinque.eric.fractalGenerator.ui.components

import javafx.scene.control.Hyperlink
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.layout.VBox

// TODO
// SideBar is responsible for composing the data from a network call into a header/body/footer.
// It makes calls to wikipedia to pull the information about a particular algorithm,
// displays the information to the user, and provides further links to follow

data class Link(val label: String, val url: String) {}

class SideBar() : Region() {
    val linkList = mutableListOf<Link>()

    val header: Label = Label()
    val body = VBox()
    val footer = HBox()

    fun build(): Region {
        val sidebar = VBox()
        sidebar.children.addAll(header, body, footer)
        return sidebar
    }

    fun updateLinks() {
        footer.children.clear()
        linkList.forEach {
            val hyperlink = Hyperlink(it.url).apply {
//                onAction = (it.url)  // url captured in lambda
            }
            footer.children.add(hyperlink)
        }
    }

    fun addLink(url: String, label: Label) {
//        linkList.add(Link(url, label))
    }
}


