package trinque.eric.fractalGenerator.drawable.fractal

import javafx.scene.canvas.GraphicsContext
import trinque.eric.fractalGenerator.geometry.Coordinate

class Circle(val origin: Coordinate, radius: Double, depth: Int, angle: Double) :
    Fractal(depth = depth, angle = angle) {

    val diameter: Double = radius * 2
    val center: Coordinate = Coordinate(origin.x - radius, origin.y - radius)

    override fun draw(gc: GraphicsContext) {
        _draw(gc, center, diameter, depth, angle)
    }

    private fun _draw(gc: GraphicsContext, origin: Coordinate, diameter: Double, depth: Int, angle: Double) {
        if (depth == 0) return
        val offset = diameter - (diameter * 0.85)
        gc.strokeOval(origin.x, origin.y, diameter, diameter)
        _draw(gc, Coordinate(origin.x + offset / 2, origin.y + offset / 2), diameter * 0.85, depth - 1, angle)
    }
}