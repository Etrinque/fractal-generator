package trinque.eric.fractalGenerator.ui.layout

import javafx.stage.Stage


class LayoutManager(primaryStage: Stage) {
    enum class LayoutMode {
        DESKTOP, // 1280px
        TABLET, // 768px
        COMPACT
    }

    private var currentMode: LayoutMode = LayoutMode.DESKTOP
    private val layoutChangeListeners = mutableListOf<(LayoutMode) -> Unit>()

    init {
        primaryStage.widthProperty().addListener { _, _, newWidth ->
            val newMode = determineLayout(newWidth.toDouble())
            if (newMode != currentMode) {
                currentMode = newMode
                notifyLayoutChange(newMode)
            }
        }
    }

    private fun determineLayout(width: Double): LayoutMode {
        return when {
            width >= 1280 -> LayoutMode.DESKTOP
            width >= 768 -> LayoutMode.TABLET
            else -> LayoutMode.COMPACT
        }
    }

    private fun notifyLayoutChange(mode: LayoutMode) {
        layoutChangeListeners.forEach { it(mode) }
    }

    fun onLayoutChange(listener: (LayoutMode) -> Unit) {
        layoutChangeListeners.add(listener)
    }
}