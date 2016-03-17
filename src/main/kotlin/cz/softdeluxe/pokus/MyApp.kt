package cz.softdeluxe.pokus

import cz.softdeluxe.pokus.view.MainView
import javafx.application.Application
import tornadofx.App
import tornadofx.importStylesheet

class MyApp: App() {

    override val primaryView = MainView::class

    init {
        importStylesheet("/app.css")
    }
}

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java, *args)
}