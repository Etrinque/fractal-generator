package trinque.eric.fractalGenerator.drawable.fractal

import javafx.scene.canvas.GraphicsContext
import trinque.eric.fractalGenerator.geometry.Coordinate

class Square(
    val startCoord: Coordinate,
    var sideLen: Double,
    var cornerRadius: Double = 0.0,
    _depth: Int,
    _angle: Double = 0.0
) : Fractal(depth = _depth, angle = _angle) {
    init {}

    override fun draw(gc: GraphicsContext) {
        drawSquare(
            gc,
            startCoord,
            sideLen,
            cornerRadius,
            depth
        )
    }

    override fun rotate(angle: Double) {}
    private fun drawSquare(
        gc: GraphicsContext,
        coord: Coordinate,
        sideLen: Double,
        cornerRadius: Double = 0.0,
        _depth: Int = 20
    ) {
        if (_depth == 0 || sideLen < 0.0) return
        val offset = (sideLen * 0.15) / 2
        drawSquare(gc, Coordinate(coord.x + offset, coord.y + offset), sideLen * 0.85, cornerRadius, _depth - 1)
        gc.strokeRoundRect(coord.x, coord.y, sideLen, sideLen, cornerRadius, cornerRadius)
    }
}