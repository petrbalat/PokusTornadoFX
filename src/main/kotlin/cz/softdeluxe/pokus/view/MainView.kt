package cz.softdeluxe.pokus.view

import cz.softdeluxe.pokus.controller.DeliciousController
import cz.softdeluxe.pokus.lib.actionEvents
import cz.softdeluxe.pokus.lib.observeOnFx
import cz.softdeluxe.pokus.lib.observeOnIo
import cz.softdeluxe.pokus.model.Post
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.scene.web.WebView
import javafx.stage.Stage
import rx.Observable
import tornadofx.*
import java.time.LocalDateTime

class MainView : View() {

    override val root: BorderPane by fxml()

    val table: TableView<Post> by fxid()

    val controller: DeliciousController by inject()

    val testBtn: Button by fxid()
    val testLbl: Label by fxid()

    init {
        title = messages["title"]

        with (table) {
            // Create table columns and bind to the data model
            column(messages["id"], Post::title).prefWidth = 50.0
            column(messages["userId"], Post::userId).prefWidth = 50.0
            column(messages["title"], Post::title).prefWidth = 200.0
            column(messages["body"], Post::body).prefWidth = 300.0

            // Handle double click on row
            onUserSelect {
                browse(it)
            }

            // Load data from the controller
            asyncItems {
                controller.recentBookmarks()
            }
        }

        testBtn.actionEvents().map {
            LocalDateTime.now().toString()
        }.switchMap { value ->
            Observable.fromCallable {
                Thread.sleep(2000)
                value
            }.observeOnIo()
        }.observeOnFx().subscribe {
            testLbl.text = it
        }
    }

    /**
     * Open the selected bookmark in a new browser window
     */
    private fun browse(post: Post) = Stage().apply {
        log.info { "Browsing ${post.id}..." }

        val webView = WebView().apply {
            engine.load(post.id.toString())//FIXME
        }
        scene = Scene(webView)
        title = post.title
        show()
    }
}