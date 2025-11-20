package trinque.eric.fractalGenerator.drawable.fractal

import javafx.scene.canvas.GraphicsContext

// Note: Could be implemented as an Interface as of now...
abstract class Fractal(var depth: Int = 0, var angle: Double = 0.0) {
    open fun draw(gc: GraphicsContext) {
    }

    open fun clear(gc: GraphicsContext) {
        gc.clearRect(0.0, 0.0, gc.canvas.width, gc.canvas.height)
    }

    open fun rotate(angle: Double) {}
}