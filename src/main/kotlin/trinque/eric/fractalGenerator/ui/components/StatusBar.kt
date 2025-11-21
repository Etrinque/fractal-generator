package trinque.eric.fractalGenerator.ui.components

import atlantafx.base.controls.Spacer
import atlantafx.base.theme.Styles
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.scene.layout.HBox
import javafx.scene.layout.Region

class StatusBar() : Region() {
    private var status = "Ready"
    private var progress = 0.0
    private val container = HBox()
    private val statusLabel = Label(status)
    private val spacer = Spacer().apply { }
    private val progressBar = ProgressBar(progress).apply {
        minWidth = 200.0
        styleClass.add("progress-bar")
        styleClass.add(Styles.SMALL)
    }

    fun build(): Region {
        container.children.addAll(statusLabel, spacer, progressBar)
        return container
    }

    fun update(msg: String?, progress: Double?) {
        updateStatus(msg)
        updateProgress(progress)
    }

    private fun updateStatus(msg: String?) {
        status = msg
            ?: when (progress) {
                0.0 -> "Ready"
                in 0.1..99.9 -> "Working..."
                else -> "Done"
            }
        statusLabel.text = status
    }

    private fun updateProgress(value: Double?) {
        if (value != null) {
            progress = value
            progressBar.progress = value
        }
    }
}