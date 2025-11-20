package trinque.eric.fractalGenerator.ui.layout

import javafx.scene.layout.BorderPane
import javafx.scene.layout.Region
import trinque.eric.fractalGenerator.ui.containers.*

class DesktopLayout(
    private val topContainer: TopContainer,
    private val rightContainer: RightContainer,
    private val bottomContainer: BottomContainer,
    private val centerContainer: CenterContainer,
    private val leftContainer: LeftContainer?
) : AppLayout {
    val root = BorderPane()

    override fun build(): Region {
        root.apply {
            top = topContainer.build()
            right = rightContainer.build()
            bottom = bottomContainer.build()
            center = centerContainer.build()
            left = leftContainer?.build()
        }
        return root
    }

    override fun updateLayout() = TODO()
}