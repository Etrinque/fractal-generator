package trinque.eric.fractalGenerator.app

import atlantafx.base.theme.NordDark
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage
import org.kordamp.ikonli.javafx.FontIcon
import trinque.eric.fractalGenerator.drawable.fractal.Circle
import trinque.eric.fractalGenerator.drawable.fractal.Sierpinski
import trinque.eric.fractalGenerator.drawable.fractal.Square
import trinque.eric.fractalGenerator.geometry.Coordinate


class App : Application() {
    override fun start(stage: Stage) {
//        val colorScheme = Platform.Preferences.get("colorScheme")
//        if (colorScheme == "Dark") {

        setUserAgentStylesheet(NordDark().userAgentStylesheet)
//        }
        // Note: Center Content
        // Todo: Make Center Content Dynamically Update
        val canvas = Canvas(600.0, 600.0)

        val gc: GraphicsContext = canvas.graphicsContext2D
        gc.stroke = Color.OLIVEDRAB
        gc.lineWidth = 2.0

        // Todo: Move Menu to class
        val menuFile = Menu("File").apply {
            items.addAll(MenuItem("Open"), MenuItem("Close"), MenuItem("Save"), MenuItem("Reserved"))
        }
        val menuAbout = Menu("About").apply {
            items.addAll(MenuItem("About Fractals"), MenuItem("About JavaFX"))
        }
        val menuView = Menu("View").apply {
            items.addAll(MenuItem("Theme"), MenuItem("View Sidebar"))
        }
        val menuBar = MenuBar().apply {
            menus.addAll(menuFile, menuView, menuAbout)
        }

        // Todo: Move TopBar to class
        var currentDepth: Int = 0
        var currentAlgorithm: String? = null

        val DepthLabel = Label("Depth")
        val DepthCtrl = Spinner<Int>(1, 25, 1).apply {
            isEditable = true
            minWidth = 100.0
            maxWidth = 100.0
            tooltip = Tooltip("Set depth of iteration")
        }

        val AngleLabel = Label("Angle")
        val AngleCtrl = Spinner<Double>(0.0, 360.0, 0.0, 0.5).apply {
            isEditable = true
            minWidth = 100.0
            maxWidth = 125.0
            tooltip = Tooltip("Set Rotation")
        }

        val StrokeLabel = Label("Stroke")
        val strokePicker = ColorPicker().apply {
            maxWidth = 75.0
            tooltip = Tooltip("Stroke Color")
        }

        val fillLabel = Label("Fill")
        val fillPicker = ColorPicker().apply {
            maxWidth = 75.0
            tooltip = Tooltip("Fill Color")
        }

        val drawLabel = Label("Algorithm").apply { id = "textLabel" }
        val drawList = FXCollections.observableArrayList<String>(
            "Sierpinski Triangle",
            "Sierpinski Carpet",
            "Square",
            "Circle",
            "Mandelbrot Set"
        )

        val Reset = Button().apply {
            id = "resetButton"
            text = ""
            graphic = FontIcon("mdi2r-refresh").apply {
                scaleX = -1.0
            }

        }
        Reset.onAction = EventHandler {
            gc.clearRect(0.0, 0.0, canvas.width, canvas.height)
            DepthCtrl.valueFactory.value = 0
        }

        fun clear() {
            gc.clearRect(0.0, 0.0, canvas.width, canvas.height)
        }

        val drawSelector = ComboBox<String>(drawList).apply {
            id = "drawSelectorComboBox"
            maxWidth = 250.0
        }

        fun draw() {
            clear()
            currentDepth = DepthCtrl.value
            when (currentAlgorithm) {
                //Note: Testing Triangle
                "Square" -> {
                    val startCoordinate = Coordinate(50.0, 50.0)
                    val sq = Square(startCoordinate, 500.00, _depth = currentDepth)
                    sq.draw(gc)
                }

                "Circle" -> {
                    val startCoordinate = Coordinate(canvas.width / 2, canvas.height / 2)
                    val circle = Circle(startCoordinate, 200.00, depth = currentDepth, angle = 0.0)
                    circle.draw(gc)
                }

                "Sierpinski Triangle" -> {
                    val tri = Sierpinski.Triangle(Coordinate(300.0, 50.0), 500.0, _depth = currentDepth, _angle = 0.0)
                    tri.draw(gc)
                }

                "Sierpinski Carpet" -> {
                    val carpet = Sierpinski.Carpet(Coordinate(50.0, 50.0), 500.0, _depth = currentDepth, _angle = 0.0)
                    carpet.draw(gc)
                }

                "Mandelbrot" -> {}
            }
        }
        // TODO: Refactor all magic numbers to user input controls
        drawSelector.onAction = EventHandler {
            currentAlgorithm = drawSelector.value
            draw()
        }

        DepthCtrl.valueProperty().addListener { _, _, newValue ->
            currentDepth = newValue
            draw()
        }

        strokePicker.valueProperty().addListener { _, _, newColor ->
            gc.stroke = newColor
            draw()
        }
        fillPicker.valueProperty().addListener { _, _, newColor ->
            gc.fill = newColor
            draw()
        }

        // Note: Top Toolbar
        val toolbar = ToolBar(
            drawLabel,
            drawSelector,
            Reset,
            Separator(Orientation.VERTICAL),
            DepthLabel,
            DepthCtrl,
            AngleLabel,
            AngleCtrl,
            Separator(Orientation.VERTICAL),
            StrokeLabel,
            strokePicker,
            fillLabel,
            fillPicker,
        ).apply {
            padding = Insets(5.0, 5.0, 5.0, 15.0)
        }

        // TODO; Refactor StatusBar to class
        // Note: StatusBar
        val spacer = Region()
        HBox.setHgrow(spacer, Priority.ALWAYS)
        val statusBar = HBox()
        statusBar.padding = Insets(5.0, 10.0, 5.0, 10.0)
        val statusMessage = Label("Ready")
        val progressBar = ProgressBar().apply {
            minWidth = 200.0
            // Todo: Customize here
        }
        statusBar.children.addAll(statusMessage, spacer, progressBar)

        // TODO: Refactor SideBar to class
        // Note: SideBar
        var sideBarTitle = Label("Header")
        var sideBarContentBody = TextArea().apply {
            isEditable = false
//            style = "-fx-background-color: rgb(225, 225, 225)"
//            minWidth = 300.0
//            minHeight = 300.0

        }
        val sideBarContent = VBox().apply {
            children.add(sideBarContentBody)
        }
        val sideBar = VBox().apply {
            padding = Insets(5.0, 15.0, 5.0, 5.0)
        }
        sideBar.children.addAll(sideBarTitle, sideBarContent)

        val topBox = VBox().apply {
            children.addAll(menuBar, toolbar)
        }

        // Note: Layout
        val root = BorderPane().apply {

            top = topBox
            center = canvas
            right = sideBar
            bottom = statusBar
        }

        // Note: Boilerplate JavaFX
        val scene = Scene(root)

        val css = javaClass.getResource("/main.css")?.toExternalForm()
        if (css != null) {
            scene.stylesheets.add(css)
        } else {
            println("Cannot locate css... Returned Path: $css")
        }

        stage.title = "Fractal Viewer"
        stage.scene = scene
        stage.show()
    }
}