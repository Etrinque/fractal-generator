package trinque.eric.fractalGenerator.ui.layout

import javafx.scene.layout.Region

interface AppLayout {
    fun build(): Region
    fun updateLayout()
}