package trinque.eric.fractalGenerator.ui.containers

import javafx.scene.layout.Region

interface ContainerLayout {
    fun build(): Region
    fun updateLayout(vararg components: Region)
}