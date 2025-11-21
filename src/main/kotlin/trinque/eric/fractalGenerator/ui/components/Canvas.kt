package trinque.eric.fractalGenerator.ui.components

import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import trinque.eric.fractalGenerator.drawable.Drawable

class Canvas(width: Double, height: Double) : Canvas() {
    val gc = graphicsContext2D.apply {
        fill = Color.BLACK
        stroke = Color.BLACK
        lineWidth = 2.0
    }

    fun render(drawable: Drawable) {
        drawable.draw(gc)
    }

    fun clear() {
        gc.clearRect(0.0, 0.0, width, height)
    }

    fun strokeColor(color: Color) {
        this.gc.stroke = color
    }

    fun fillColor(color: Color) {
        this.gc.fill = color
    }

    // the default stroke line width is 2.0pt - up from 1.0pt
    fun lineWidth(width: Double) {
        this.gc.lineWidth = width
    }
}
