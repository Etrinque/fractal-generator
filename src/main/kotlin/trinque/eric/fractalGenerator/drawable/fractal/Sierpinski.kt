package trinque.eric.fractalGenerator.drawable.fractal

import javafx.scene.canvas.GraphicsContext
import trinque.eric.fractalGenerator.geometry.Coordinate

class Sierpinski() : Fractal() {
    init {}

    data class Vertices(
        val P1: Coordinate, val P2: Coordinate, val P3: Coordinate
    )

    class Triangle(
        val startCoord: Coordinate,
        var base: Double,
        val height: Double = base,
        val _depth: Int,
        val _angle: Double
    ) : Fractal(depth = _depth, angle = _angle) {
        val startTriangle = Vertices(
            P1 = startCoord,
            Coordinate(startCoord.x - base / 2, startCoord.y + height),
            Coordinate(startCoord.x + base / 2, startCoord.y + height)
        )
        val xPts: DoubleArray =
            arrayOf<Double>(startTriangle.P1.x, startTriangle.P2.x, startTriangle.P3.x).toDoubleArray()
        val yPts: DoubleArray =
            arrayOf<Double>(startTriangle.P1.y, startTriangle.P2.y, startTriangle.P3.y).toDoubleArray()

        override fun draw(gc: GraphicsContext) {
            gc.strokePolygon(xPts, yPts, 3)
            draw(gc, startTriangle, depth)
        }

        private fun draw(gc: GraphicsContext, origin: Vertices, depth: Int) {
            if (depth == 0) return
            val AB = Coordinate((origin.P1.x + origin.P2.x) / 2, (origin.P1.y + origin.P2.y) / 2)
            val BC = Coordinate((origin.P2.x + origin.P3.x) / 2, (origin.P2.y + origin.P3.y) / 2)
            val AC = Coordinate((origin.P1.x + origin.P3.x) / 2, (origin.P1.y + origin.P3.y) / 2)

            val next = Vertices(
                // Todo: Add calculations
                AB,
                BC,
                AC
            )
            gc.strokePolygon(
                arrayOf<Double>(next.P1.x, next.P2.x, next.P3.x).toDoubleArray(),
                arrayOf<Double>(next.P1.y, next.P2.y, next.P3.y).toDoubleArray(), 3
            )

//            draw(gc, next, depth - 1)
            draw(gc, Vertices(Coordinate(origin.P1.x, origin.P1.y), AB, AC), depth - 1)
            draw(gc, Vertices(AB, Coordinate(origin.P2.x, origin.P2.y), BC), depth - 1)
            draw(gc, Vertices(AC, BC, Coordinate(origin.P3.x, origin.P3.y)), depth - 1)
        }

        override fun rotate(angle: Double) {}
    }

    // TODO: Fix Coordinate system to calculate Canvas center, offset from there.
    class Carpet(val startCoord: Coordinate, var sideLength: Double, val _depth: Int, val _angle: Double) :
        Fractal(depth = _depth, angle = _angle) {
        override fun draw(gc: GraphicsContext) {
            draw(gc, startCoord, sideLength, depth, angle)
        }

        private fun draw(gc: GraphicsContext, coord: Coordinate, sideLength: Double, depth: Int, angle: Double = 0.0) {
            if (depth == 0 || sideLength <= 1) return

            val segments: MutableList<Coordinate> = MutableList(9) { Coordinate() }

            val sectorSize = ((sideLength / 3))
            for (i in 0..2) {
                segments[i] = (Coordinate(coord.x + (sectorSize * i), coord.y))
                segments[3 + i] = (Coordinate(coord.x + (sectorSize * i), coord.y + (sectorSize * 1)))
                segments[6 + i] = (Coordinate(coord.x + (sectorSize * i), coord.y + (sectorSize * 2)))
            }

            val center = segments[4]

            gc.fillRect(center.x, center.y, sectorSize, sectorSize)

            for (i in segments.indices) {
                if (i != 4) {
                    draw(gc, segments[i], sectorSize, depth - 1, angle)
                }
            }


        }

        override fun rotate(angle: Double) {}
    }

}